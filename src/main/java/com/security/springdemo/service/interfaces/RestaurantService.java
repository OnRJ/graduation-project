package com.security.springdemo.service.interfaces;

import com.security.springdemo.model.Restaurant;
import java.util.List;

public interface RestaurantService {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int id);
}
