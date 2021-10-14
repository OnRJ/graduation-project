package com.dailymenurating.rest;


import com.dailymenurating.model.Restaurant;
import com.dailymenurating.service.interfaces.RestaurantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dailymenurating.util.ValidationUtil.checkId;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/restaurants")
public class RestaurantController {
    private static final Logger LOG = getLogger(RestaurantController.class);
    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('user:read')")
    public Restaurant get(@PathVariable Integer id) {
        LOG.info("get restaurant with id {}", id);
        return service.get(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Restaurant> getAll() {
        LOG.info("getAll restaurants");
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        LOG.info("create restaurant {}", restaurant);
        return service.save(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable Integer id) {
        LOG.info("delete restaurant with id {}", id);
        service.delete(id);
    }
}
