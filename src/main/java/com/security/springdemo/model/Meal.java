package com.security.springdemo.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.menu.id=:menuId"),
        @NamedQuery(name = Meal.GET_ALL_FROM_MENU, query = "SELECT m FROM Meal m WHERE m.menu.id=:menuId"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.menu.id=:menuId")
})
@Entity
@Table(name = "meal")
public class Meal extends AbstractNamedEntity {

    public static final String GET = "Meal.get";
    public static final String GET_ALL_FROM_MENU = "Meal.getAllFromMenu";
    public static final String GET_ALL = "Meal.getAll";
    public static final String DELETE = "Meal.delete";

    private double price;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Meal() {
    }

    public Meal(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menu = null;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}