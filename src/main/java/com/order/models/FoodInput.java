package com.order.models;

public class FoodInput {
    private String food;
    private int quantity;
    private Float cost;

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

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{food:"+food+", quantity:"+quantity+", cost:"+cost+"}";
    }
}
