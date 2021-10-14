package com.dailymenurating.repository.implementations;

import com.dailymenurating.model.Restaurant;
import com.dailymenurating.model.User;
import com.dailymenurating.model.Vote;
import com.dailymenurating.repository.interfaces.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, Integer userId, int restaurantId) {
        vote.setUser(em.getReference(User.class, userId));
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        vote.setDate(LocalDate.now());
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public List<Vote> getAll(LocalDate date) {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public Vote getByUserIdAndDate(Integer userId, LocalDate date) {
        List<Vote> votes = em.createNamedQuery(Vote.GET_BY_USER_AND_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .getResultList();

        return votes.size() == 0 ? null : votes.get(0);
    }

    @Override
    public List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date) {
        return em.createNamedQuery(Vote.GET_BY_RESTAURANT_AND_DATE, Vote.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
}
