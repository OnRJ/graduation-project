package com.dailymenurating.service.interfaces;

import com.dailymenurating.model.Menu;
import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);

    Menu save(Menu menu, int restaurantId);

    void delete(int id, int restaurantId);
}
