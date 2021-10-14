package com.dailymenurating.service.interfaces;

import com.dailymenurating.model.Restaurant;
import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int id);
}
