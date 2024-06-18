package com.order.controllers.model;

public class Food {
    public Food(String food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    private String food;
    private int quantity;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
