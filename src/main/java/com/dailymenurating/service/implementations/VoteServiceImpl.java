package com.dailymenurating.service.implementations;

import com.dailymenurating.repository.interfaces.UserRepository;
import com.dailymenurating.repository.interfaces.VoteRepository;
import com.dailymenurating.service.interfaces.VoteService;
import com.dailymenurating.util.ValidationUtil;
import com.dailymenurating.util.exception.AccessRightsException;
import com.dailymenurating.util.exception.VoteException;
import com.dailymenurating.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Vote save(Vote vote, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");

        Integer userId = userRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()).getId();

        Vote oldVote = repository.getByUserIdAndDate(userId, LocalDate.now());

        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            return repository.save(Objects.requireNonNullElse(oldVote, vote), userId, restaurantId);
        } else {
            throw new VoteException("Voting is over for today");
        }
    }

    @Override
    public List<Vote> getAll(LocalDate date) {
        return repository.getAll(date);
    }

    @Override
    public List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return repository.getByRestaurantIdAndDate(restaurantId, date);
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }
}
