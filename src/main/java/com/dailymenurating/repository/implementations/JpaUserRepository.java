package com.dailymenurating.repository.implementations;

import com.dailymenurating.model.User;
import com.dailymenurating.repository.interfaces.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByEmail(String email) {
        return em.createNamedQuery(User.GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }
}
