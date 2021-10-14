package com.dailymenurating.repository.interfaces;


import com.dailymenurating.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int menuId);

    Meal get(int id, int menuId);

    List<Meal> getAll();

    List<Meal> getAllFromMenu(int menuId);

    boolean delete(int id, int menuId);
}
