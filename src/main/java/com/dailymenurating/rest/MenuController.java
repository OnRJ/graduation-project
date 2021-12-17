package com.dailymenurating.rest;

import com.dailymenurating.service.interfaces.MenuService;
import com.dailymenurating.model.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/rest/menus", method = RequestMethod.GET)
public class MenuController {
    private final MenuService service;

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Menu> getAll() {
        List<Menu> menus = service.getAll();
        log.info("Get all menus {}", menus);
        return menus;
    }

    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public List<Menu> getAllByDate(@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Menu> menus = service.getAllByDate(date);
        log.info("Get all menus with date {} {} ", date, menus);
        return menus;
    }

    @PostMapping("/menu/")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public Menu create(@RequestBody Menu menu) {
        int restaurantId = menu.getRestaurant().getId();
        Menu newMenu= service.save(menu, restaurantId);
        log.info("Create menu {} for restaurant {}", newMenu, restaurantId);
        return newMenu;
    }

    @DeleteMapping("/{id}/restaurantId={restaurantId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void delete(@PathVariable Integer id, @PathVariable Integer restaurantId) {
        log.info("Delete menu {}", service.get(id, restaurantId));
        service.delete(id, restaurantId);
    }
}
