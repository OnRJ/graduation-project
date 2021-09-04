package com.security.springdemo.service.interfaces;

import com.security.springdemo.model.Vote;
import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    List<Vote> getAll(LocalDate date);

    List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Vote save(Vote vote, Integer userId, int restaurantId);

    void delete(int id);
}
