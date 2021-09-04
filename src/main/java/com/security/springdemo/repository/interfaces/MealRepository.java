package com.security.springdemo.repository.interfaces;


import com.security.springdemo.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int menuId);

    Meal get(int id, int menuId);

    List<Meal> getAll();

    List<Meal> getAllFromMenu(int menuId);

    boolean delete(int id, int menuId);
}
