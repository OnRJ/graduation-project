package com.dailymenurating.service.implementations;

import com.dailymenurating.repository.interfaces.RestaurantRepository;
import com.dailymenurating.model.Restaurant;
import com.dailymenurating.service.interfaces.RestaurantService;
import com.dailymenurating.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}
