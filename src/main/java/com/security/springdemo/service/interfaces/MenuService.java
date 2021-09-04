package com.security.springdemo.service.interfaces;

import com.security.springdemo.model.Menu;
import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);

    Menu save(Menu menu, int restaurantId);

    void delete(int id, int restaurantId);
}
