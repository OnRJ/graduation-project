package com.dailymenurating.rest;

import com.dailymenurating.model.Restaurant;
import com.dailymenurating.service.interfaces.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/restaurants")
public class RestaurantController {
    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public Restaurant get(@PathVariable Integer id) {
        Restaurant restaurant = service.get(id);
        log.info("Get restaurant {}", restaurant);
        return restaurant;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Restaurant> getAll() {
        List<Restaurant> restaurantsList = service.getAll();
        log.info("Get all restaurants {}", restaurantsList);
        return restaurantsList;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = service.save(restaurant);
        log.info("Create restaurant {}", newRestaurant);
        return newRestaurant;
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable Integer id) {
        log.info("Delete restaurant {}", service.get(id));
        service.delete(id);
    }
}
