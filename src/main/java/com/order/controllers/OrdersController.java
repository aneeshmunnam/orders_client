package com.order.controllers;

import com.order.models.Food;
import com.order.models.FoodInput;
import com.order.models.Order;
import com.order.models.OrderInput;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
public class OrdersController {

    private final List<Order> orders = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    @QueryMapping
    public List<Order> getOrders() {
        System.out.println("fetching orders");
        return orders;
    }

    @QueryMapping
    public String createOrder(@Argument OrderInput order) {
        long currentTime = System.currentTimeMillis();
        if (order != null && order.getFoods() != null && !order.getFoods().isEmpty()) {
            Order createdOrder = orderService.createOrder(order);
            System.out.println("To create order and queue it took: "+(System.currentTimeMillis()-currentTime)+ " ms.");
            return "Order total is: "+createdOrder.getTotal();
        } else {
            return "Order is empty. Please add something to order";
        }
    }

}
