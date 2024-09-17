package com.order.service;

import com.order.models.Food;
import com.order.models.FoodInput;
import com.order.models.Order;
import com.order.models.OrderInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Value("${queueLocation.path}")
    private String pathLocation;
    private int i = 0;

    public Order createOrder(OrderInput order) {
        // Push to queue
        CompletableFuture<String> queuing = CompletableFuture.supplyAsync(() -> {
            System.out.println("Pushing to directory");
            try {
                File dirPath = new File(pathLocation);
                if (!dirPath.exists()) {
                    if (dirPath.mkdirs()) {
                        System.out.println("Directory created for store: "+order.getStoreId());
                    }
                }
                File filepath = new File(pathLocation+(i++)+".log");
                FileOutputStream fos = new FileOutputStream(filepath);
                fos.write(order.toString().getBytes(StandardCharsets.UTF_8));
                filepath.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "message created with order:" +order.getOrderId() + " for store:"+ order.getStoreId();
        });
        System.out.println("Object Mapping order to orders");
        Order createOrder = new Order();
        createOrder.setOrderId(order.getOrderId());
        createOrder.setStoreId(order.getStoreId());
        createOrder.setFoods(convertInputToFood(order.getFoods()));
        createOrder.setStatus("ORDER CREATED");
        Float total = 0.0f;
        for (FoodInput food: order.getFoods()) {
            if (food.getFood() != null && !food.getFood().isEmpty() && food.getQuantity() != 0) {
                total += food.getCost() * food.getQuantity();
            }
        }
        createOrder.setTotal(total);
        try {
            String location = queuing.get();
            System.out.println(location);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return createOrder;
    }

    private List<Food> convertInputToFood(List<FoodInput> foodInputs) {
        return foodInputs.stream()
                .map(foodInput -> {
                    Food food = new Food();
                    food.setFood(foodInput.getFood());
                    food.setCost(foodInput.getCost());
                    food.setQuantity(foodInput.getQuantity());
                    return food;
                }).collect(Collectors.toList());
    }
}
