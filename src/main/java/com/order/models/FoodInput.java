package com.order.models;

public class FoodInput {
    private String food;
    private Float quantity;
    private Float cost;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
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
