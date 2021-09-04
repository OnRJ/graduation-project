package com.security.springdemo.rest;

import com.security.springdemo.model.Meal;
import com.security.springdemo.service.interfaces.MealService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.security.springdemo.util.ValidationUtil.checkId;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/meals")
public class MealController {
    private static final Logger LOG = getLogger(MealController.class);
    private final MealService service;

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Meal> getAll() {
        LOG.info("getAll meals");
        return service.getAll();
    }

    @GetMapping("/menuId={menuId}")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Meal> getAllFromMenu(@PathVariable String menuId) {
        LOG.info("getAll meals for menu {}", menuId);
        int checkedMenuId = checkId(menuId);
        return service.getAllMealsFromMenu(checkedMenuId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Meal create(@RequestBody Meal meal) {
        int menuId = meal.getMenu().getId();
        LOG.info("create meal for menu {}", menuId);
        return service.save(meal, menuId);
    }

    @DeleteMapping("/{id}/menuId={menuId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable String id, @PathVariable String menuId) {
        LOG.info("delete meal with id {}", id);
        int checkedMealId = checkId(id);
        int checkedMenuId = checkId(menuId);
        service.delete(checkedMealId, checkedMenuId);
    }
}
