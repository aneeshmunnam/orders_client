package com.order.controllers;

import com.order.controllers.model.Order;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {

    private List<Order> orders = new ArrayList<>();

    @QueryMapping
    public List<Order> getOrders() {
        System.out.println("fetching Order");
        return orders;
    }

    @QueryMapping
    public String createOrder(@Argument Order order) {

        return "order created";
    }
}
