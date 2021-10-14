package com.dailymenurating.service.implementations;

import com.dailymenurating.repository.interfaces.MealRepository;
import com.dailymenurating.model.Meal;
import com.dailymenurating.service.interfaces.MealService;
import com.dailymenurating.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal, int menuId) {
        Assert.notNull(meal,"meal must not be null");
        return repository.save(meal, menuId);
    }

    @Override
    public Meal get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, menuId), id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Meal> getAllMealsFromMenu(int menuId) {
        return repository.getAllFromMenu(menuId);
    }

    @Override
    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id, menuId), id);
    }
}
