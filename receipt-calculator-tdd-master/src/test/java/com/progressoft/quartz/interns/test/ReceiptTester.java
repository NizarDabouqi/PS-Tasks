package com.progressoft.quartz.interns.test;

import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.MealManager;
import com.progressoft.quartz.intern.manager.MealManagerImpl;
import com.progressoft.quartz.intern.utility.Meal;
import com.progressoft.quartz.intern.utility.Receipt;
import com.progressoft.quartz.intern.utility.ReceiptCalculator;
import com.progressoft.quartz.intern.utility.ReceiptCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTester {

    private ReceiptCalculator receiptCalculator;
    private MealManager mealManager;

    @BeforeEach
    void init() {
        mealManager = new MealManagerImpl(); // Initialize your MealManager implementation here
        receiptCalculator = new ReceiptCalculatorImpl(); // Initialize your ReceiptCalculator implementation here
        if (mealManager == null) {
            fail("Please initialize mealManager");
        }
        if (receiptCalculator == null) {
            fail("Please initialize receiptCalculator");
        }
    }

    @Test
    public void givenNullMeal_whenCalculateReceipt_thenThrowReceiptException() {
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(null, 0, 0));
        assertEquals("Meals must not be null", exception.getMessage());
    }

    @Test
    public void givenEmptyMeal_whenCalculateReceipt_thenThrowReceiptException() {
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(new ArrayList<>(), 0, 0));
        assertEquals("Meals must not be empty", exception.getMessage());
    }

    @Test
    public void givenZeroNumberOfCustomers_whenCalculateReceipt_thenThrowReceiptException() {
        ArrayList<Meal> mealsById = getMeals();
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(mealsById, 0, 0));
        assertEquals("Number of Customers must be greater than 0", exception.getMessage());
    }

    @Test
    public void givenValidArgumentsWithTwoCustomers_whenCalculateReceipt_thenGetReceipt() {
        ArrayList<Meal> mealsById = getMeals();
        Receipt receipt = receiptCalculator.calculateReceipt(mealsById, 2, 2);
        assertEquals(3, receipt.getMeals().size());
        assertEquals(13.5, receipt.getTotal());
        assertEquals(1, receipt.getTax());
    }

    @Test
    public void givenValidArgumentsWithOneCustomers_whenCalculateReceipt_thenGetReceipt() {
        ArrayList<Meal> mealsById = getMeals();
        Receipt receipt = receiptCalculator.calculateReceipt(mealsById, 2, 1);
        assertEquals(3, receipt.getMeals().size());
        assertEquals(14.5, receipt.getTotal());
        assertEquals(2, receipt.getTax());
    }

    private ArrayList<Meal> getMeals() {
        mealManager.loadMealsFromFile(getFilePath("sample/meals.csv"));
        HashMap<String, Integer> mealsWithQuantity = new HashMap<>();
        mealsWithQuantity.put("2", 1);
        mealsWithQuantity.put("6", 2);
        mealsWithQuantity.put("3", 1);
        ArrayList<Meal> mealsById = mealManager.getMealsById(mealsWithQuantity, (HashMap<String, Meal>) mealManager.getMeals());
        return mealsById;
    }

    private String getFilePath(String resourcePath) {
        URL resource = this.getClass().getClassLoader().getResource(resourcePath);
        return resource.getPath();
    }
}
