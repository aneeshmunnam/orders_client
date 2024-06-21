package com.order.controllers;

import com.order.models.Food;
import com.order.models.FoodInput;
import com.order.models.Order;
import com.order.models.OrderInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersController {

    private final List<Order> orders = new ArrayList<>();

    @QueryMapping
    public List<Order> getOrders() {
        System.out.println("fetching orders");
        return orders;
    }

    @QueryMapping
    public String createOrder(@Argument OrderInput order) {
        if (order != null && order.getFoods() != null && !order.getFoods().isEmpty()) {
            Order createOrder = new Order();
            createOrder.setOrderId(order.getOrderId());
            createOrder.setStoreId(order.getStoreId());
            createOrder.setFoods(convertInputToFood(order.getFoods()));
            createOrder.setStatus("ORDER CREATED");
            Float total = 0.0f;
            for (FoodInput food: order.getFoods()) {
                if (food.getFood() != null && !food.getFood().isEmpty()) {
                    total += food.getCost();
                } else {
                    return "Food value cannot be empty. Please provide an item from the list.";
                }
            }
            createOrder.setTotal(total);
            orders.add(createOrder);
            // Push to queue
            return "Order total is: "+total;
        } else {
            return "Order is empty. Please add something to order";
        }
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
