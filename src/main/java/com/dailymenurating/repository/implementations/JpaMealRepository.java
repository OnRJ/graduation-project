package com.dailymenurating.repository.implementations;

import com.dailymenurating.repository.interfaces.MealRepository;
import com.dailymenurating.model.Meal;
import com.dailymenurating.model.Menu;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int menuId) {
        if (!meal.isNew() && get(meal.getId(), menuId) == null) {
            return null;
        }
        meal.setMenu(em.getReference(Menu.class, menuId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    public Meal get(int id, int menuId) {
        return em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll() {
        return em.createNamedQuery(Meal.GET_ALL, Meal.class)
                .getResultList();
    }

    @Override
    public List<Meal> getAllFromMenu(int menuId) {
        return em.createNamedQuery(Meal.GET_ALL_FROM_MENU, Meal.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id, int menuId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .executeUpdate() != 0;
    }
}
