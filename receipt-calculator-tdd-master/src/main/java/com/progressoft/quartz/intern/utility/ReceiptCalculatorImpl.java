package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.ReceiptException;

import java.util.ArrayList;

public class ReceiptCalculatorImpl implements ReceiptCalculator {
    @Override
    public Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers) {
        validateData(meals, numberOfCustomers);

        double total = 0.0;
        for (Meal meal : meals) {
            total += meal.getPrice() * meal.getQuantity();
        }

        double taxPerCustomer = tax / numberOfCustomers;
        double totalWithTax = total + taxPerCustomer;

        return new Receipt(meals, totalWithTax, taxPerCustomer);
    }


    private static void validateData(ArrayList<Meal> meals, int numberOfCustomers) {
        if (meals == null) {
            throw new ReceiptException("Meals must not be null");
        }

        if (meals.isEmpty()) {
            throw new ReceiptException("Meals must not be empty");
        }

        if (numberOfCustomers <= 0) {
            throw new ReceiptException("Number of Customers must be greater than 0");
        }
    }
}




