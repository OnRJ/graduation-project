package com.dailymenurating.repository.interfaces;

import com.dailymenurating.model.Menu;
import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByDate(LocalDate date);

    List<Menu> getAllByDate(LocalDate date, int restaurantId);

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    void deleteAll();
}
