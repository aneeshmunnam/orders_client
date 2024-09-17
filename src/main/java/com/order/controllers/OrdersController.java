package com.order.controllers;

import com.order.models.Order;
import com.order.models.OrderInput;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Controller
public class OrdersController {

    private final Map<Long, Order> orderDetails = new ConcurrentHashMap<>();

    @Autowired
    private OrderService orderService;

    @QueryMapping
    public List<Order> getOrders() {
        System.out.println("fetching orders");
        return new ArrayList<>(orderDetails.values());
    }

    @QueryMapping
    public String createOrder(@Argument OrderInput order) {
        long currentTime = System.currentTimeMillis();
        if (order != null && order.getFoods() != null && !order.getFoods().isEmpty()) {
            Order createdOrder = orderService.createOrder(order);
            orderDetails.put(createdOrder.getOrderId(), createdOrder);
            System.out.println("To create order and queue it took: "+(System.currentTimeMillis()-currentTime)+ " ms.");
            return "Order total is: "+createdOrder.getTotal();
        } else {
            return "Order is empty. Please add something to order";
        }
    }

    @QueryMapping
    public String orderStatus(@Argument Long orderId) {
        if (orderDetails.containsKey(orderId)) {
            return orderDetails.get(orderId).getStatus();
        } else {
            return "Order is not there";
        }
    }

    @QueryMapping
    public List<Order> getOrdersForStore(@Argument Long storeId) {
        return orderDetails.values().stream().filter((order) -> order.getStoreId() == storeId).collect(Collectors.toList());
    }

    @QueryMapping
    public String deleteOrder(@Argument Long orderId) {
        if (orderDetails.containsKey(orderId)) {
            orderDetails.remove(orderId);
            return "OrderId: "+orderId+" successfully deleted";
        } else {
            return "OrderId: "+orderId+" is not present";
        }
    }

}
