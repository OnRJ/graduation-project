package com.dailymenurating.repository.interfaces;

import com.dailymenurating.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, Integer userId, int restaurantId);

    List<Vote> getAll(LocalDate date);

    Vote getByUserIdAndDate(Integer userId, LocalDate date);

    List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date);

    boolean delete(int id);
}