package com.security.springdemo.service.interfaces;

import com.security.springdemo.model.Meal;
import java.util.List;

public interface MealService {

    Meal get(int id, int menuId);

    List<Meal> getAll();

    List<Meal> getAllMealsFromMenu(int menuId);

    Meal save(Meal meal, int menuId);

    void delete(int id, int menuId);
}
