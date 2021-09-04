package com.security.springdemo.service.implementations;

import com.security.springdemo.repository.interfaces.UserRepository;
import com.security.springdemo.repository.interfaces.VoteRepository;
import com.security.springdemo.service.interfaces.VoteService;
import com.security.springdemo.util.ValidationUtil;
import com.security.springdemo.util.exception.AccessRightsException;
import com.security.springdemo.util.exception.VoteException;
import com.security.springdemo.model.Vote;
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
    public Vote save(Vote vote, Integer userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");

        if(!userRepository.findById(userId).getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new AccessRightsException("Access is denied");
        }

        Vote oldVote = repository.getByUserIdAndDate(userId, LocalDate.now());

        if (LocalTime.now().isBefore(LocalTime.of(19, 0))) {
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
