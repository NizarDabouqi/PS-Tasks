package com.progressoft.quartz.intern.manager;


import com.progressoft.quartz.intern.utility.Meal;

import java.util.ArrayList;
import java.util.HashMap;

public interface MealManager {

    ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals);

    Object getMealsSize();

    boolean isMealRegistered(String id);

    Object getMeals();

    void saveMeal(Meal meal);

    Meal getMeal(String id);

    void removeMeal(Meal meal);

    void loadMealsFromFile(String filePath);


}
