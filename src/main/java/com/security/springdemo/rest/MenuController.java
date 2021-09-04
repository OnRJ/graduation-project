package com.security.springdemo.rest;

import com.security.springdemo.service.interfaces.MenuService;
import com.security.springdemo.model.Menu;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.security.springdemo.util.ValidationUtil.checkId;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/menus", method = RequestMethod.GET)
public class MenuController {
    private static final Logger LOG = getLogger(MenuController.class);
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Menu> getAll() {
        LOG.info("getAll menus");
        return service.getAll();
    }

    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Menu> getAllByDate(@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll menus with date {}", date);
        return service.getAllByDate(date);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Menu create(@RequestBody Menu menu) {
        int restaurantId = menu.getRestaurant().getId();
        LOG.info("create menu {} for restaurant {}", menu, restaurantId);
        return service.save(menu, restaurantId);
    }

    @DeleteMapping("/{id}/restaurantId={restaurantId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable String id, @PathVariable String restaurantId) {
        LOG.info("delete menu with id {} for restaurant {}", id, restaurantId);
        int checkedMealId = checkId(id);
        int checkedRestaurantId = checkId(restaurantId);
        service.delete(checkedMealId, checkedRestaurantId);
    }
}