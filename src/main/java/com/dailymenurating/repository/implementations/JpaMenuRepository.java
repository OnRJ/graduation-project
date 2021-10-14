package com.dailymenurating.repository.implementations;

import com.dailymenurating.repository.interfaces.MenuRepository;
import com.dailymenurating.model.Menu;
import com.dailymenurating.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepository implements MenuRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Menu get(int id, int restaurantId) {
        Menu menu = em.find(Menu.class, id);
        return menu != null && menu.getRestaurant().getId() == restaurantId ? menu : null;
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.GET_ALL, Menu.class)
                .getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return em.createNamedQuery(Menu.GET_ALL_BY_DATE, Menu.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date, int restaurantId) {
        return em.createNamedQuery(Menu.GET_ALL_BY_DATE_AND_RESTAURANT, Menu.class)
                .setParameter("date", date)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (menu.isNew()) {
            menu.setDate(LocalDate.now());
            em.persist(menu);
            return menu;
        }
        menu.setDate(menu.getDate());
        return em.merge(menu);
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public void deleteAll() {
        em.createNamedQuery(Menu.DELETE_ALL).executeUpdate();
    }
}
