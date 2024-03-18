package com.progressoft.quartz.intern.utility;

import java.util.ArrayList;

public class Receipt {

    private ArrayList<Meal> meals;
    private double total;
    private double tax;

    public Receipt(ArrayList<Meal> meals, double total, double tax) {
        this.meals = meals;
        this.total = total;
        this.tax = tax;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
