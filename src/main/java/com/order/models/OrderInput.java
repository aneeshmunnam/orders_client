package com.order.models;

import java.util.List;

public class OrderInput {
    private long orderId;
    private long storeId;
    private List<FoodInput> foods;

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

    public List<FoodInput> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodInput> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "orderId:" + orderId +
                ",storeId:" + storeId +
                ",foods:" + foods;
    }
}
