package com.order.controllers.model;

import java.util.List;

public class Order {

    private long orderId;
    private long storeId;
    private List<Food> foods;

    public Order(long orderId, long storeId, List<Food> foods) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.foods = foods;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
