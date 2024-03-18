package com.progressoft.quartz.intern.manager;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.utility.Meal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MealManagerImpl implements MealManager {

    private final Map<String, Meal> meals = new HashMap<>();

    @Override
    public ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals) {
        if (mealsIdWithQuantity == null) {
            throw new MealException("Meals Id With Quantity must not be null");
        }

        if (meals == null) {
            throw new MealException("Meals must not be null");
        }

        ArrayList<Meal> result = new ArrayList<>();

        for (String mealId : mealsIdWithQuantity.keySet()) {
            if (meals.containsKey(mealId)) {
                int quantity = mealsIdWithQuantity.get(mealId);
                Meal meal = meals.get(mealId);
                meal.setQuantity(quantity);
                result.add(meal);
            } else {
                throw new MealException("Meal with id " + mealId + " is not found");
            }
        }

        return result;
    }


    @Override
    public Object getMealsSize() {
        return meals.size();
    }

    @Override
    public boolean isMealRegistered(String id) {
        return meals.containsKey(id);
    }

    @Override
    public Map<String, Meal> getMeals() {
        return new HashMap<>(meals);
    }

    @Override
    public void saveMeal(Meal meal) {
        if (meal == null) {
            throw new MealException("Meal must not be null");
        }

        if (meal.getId() == null) {
            throw new MealException("Meal ID must not be null");
        }

        if (meals.containsKey(meal.getId())) {
            throw new MealException("Meal with id " + meal.getId() + " already registered");
        }

        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getMeal(String id) {
        // TODO, you can call !isMealRegistered(id)
        if (!isMealRegistered(id)) {
            throw new MealException("Meal with ID " + id + " is not registered");
        }
        return meals.get(id);
    }

    @Override
    public void removeMeal(Meal meal) {
        if (meal == null) {
            throw new MealException("Meal must not be null");
        }

        if (meal.getId() == null) {
            throw new MealException("Meal ID must not be null");
        }

        // TODO, you can call !isMealRegistered(id)
        if (!isMealRegistered(meal.getId())) {
            throw new MealException("Meal with ID " + meal.getId() + " is not registered");
        }

        meals.remove(meal.getId());

    }

    @Override
    public void loadMealsFromFile(String filePath) {
        if (filePath == null) {
            throw new MealException("Path must not be null");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new MealException("File Path sent does not exists");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Set<String> processedIds = new HashSet<>();

            int counter = 0;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || counter == 0) {
                    counter++;
                    continue;
                }
                processMealFromCSV(line, processedIds);
                counter++;
            }
        } catch (IOException e) {
            throw new MealException("Error loading meals from file: " + e.getMessage());
        }
    }

    private void processMealFromCSV(String line, Set<String> processedIds) {
        // TODO, keep each function, from 13-15 line of code
        // you can extract your methods into multiple functions
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new MealException("One of the lines parsed size is incorrect");
        }

        String id = parts[0].trim();
        String name = parts[1].trim();
        String description = parts[2].trim();
        String priceStr = parts[3].trim();

        validateData(processedIds, id, name, description, priceStr);

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            throw new MealException("Invalid Price Format");
        }

        Meal meal = createMealFromParts(parts, price);
        meals.put(meal.getId(), meal);
    }

    private static void validateData(Set<String> processedIds, String id, String name, String description, String priceStr) {
        if (id.isEmpty()) {
            throw new MealException("Meal id can not be empty");
        }

        if (name.isEmpty()) {
            throw new MealException("Meal name can not be empty");
        }

        if (description.isEmpty()) {
            throw new MealException("Meal description can not be empty");
        }

        if (priceStr.isEmpty()) {
            throw new MealException("Meal price can not be empty");
        }


        if (!processedIds.add(id)) {
            throw new MealException("Meal with id " + id + " is already registered");
        }
    }

    private Meal createMealFromParts(String[] parts, double price) {
        Meal meal = new Meal();
        meal.setId(parts[0]);
        meal.setName(parts[1]);
        meal.setDescription(parts[2]);
        meal.setPrice(price);

        return meal;
    }
}


