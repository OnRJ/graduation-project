package com.dailymenurating.rest;

import com.dailymenurating.model.Meal;
import com.dailymenurating.service.interfaces.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.dailymenurating.util.ValidationUtil.checkId;

@Slf4j
@RestController
@RequestMapping("/rest/meals")
public class MealController {
    private final MealService service;

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Meal> getAll() {
        List<Meal> mealList = service.getAll();
        log.info("Get all meals {}", mealList);
        return mealList;
    }

    @GetMapping("/menuId={menuId}")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Meal> getAllFromMenu(@PathVariable Integer menuId) {
        List<Meal> mealList = service.getAllMealsFromMenu(menuId);
        log.info("Get all meals for menu {} {} ", menuId, mealList);
        return mealList;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Meal create(@RequestBody Meal meal) {
        int menuId = meal.getMenu().getId();
        Meal newMeal = service.save(meal, menuId);
        log.info("Create meal {} for menu {}", newMeal, menuId);
        return newMeal;
    }

    @DeleteMapping("/{id}/menuId={menuId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable String id, @PathVariable String menuId) {
        int checkedMealId = checkId(id);
        int checkedMenuId = checkId(menuId);
        log.info("Delete meal {}", service.get(checkedMealId, checkedMenuId));
        service.delete(checkedMealId, checkedMenuId);
    }
}
