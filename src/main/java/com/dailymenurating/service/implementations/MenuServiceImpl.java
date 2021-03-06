package com.dailymenurating.service.implementations;

import com.dailymenurating.repository.interfaces.MenuRepository;
import com.dailymenurating.service.interfaces.MenuService;
import com.dailymenurating.model.Menu;
import com.dailymenurating.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public void setMealRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepository.getAllByDate(date);
    }

    @Override
    public Menu save(Menu menu, int restaurantId) {
        Assert.notNull(menu, "some menu is required");
        return menuRepository.save(menu, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(menuRepository.delete(id, restaurantId), id);
    }
}
