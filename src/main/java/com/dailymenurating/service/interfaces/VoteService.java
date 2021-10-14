package com.dailymenurating.service.interfaces;

import com.dailymenurating.model.Vote;
import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    List<Vote> getAll(LocalDate date);

    List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Vote save(Vote vote, int restaurantId);

    void delete(int id);
}
