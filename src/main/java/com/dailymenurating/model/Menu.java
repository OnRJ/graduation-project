package com.dailymenurating.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE, query = "SELECT m FROM Menu m WHERE m.date=:date"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE_AND_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.date=:date" +
                " AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.DELETE_ALL, query = "DELETE FROM Menu m")
})
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu extends AbstractBaseEntity {

    public static final String GET_ALL = "Menu.getAll";
    public static final String GET_ALL_BY_DATE = "Menu.getAllByDate";
    public static final String GET_ALL_BY_DATE_AND_RESTAURANT = "Menu.getAllByDateAndRestaurant";
    public static final String DELETE = "Menu.delete";
    public static final String DELETE_ALL = "Menu.deleteAll";

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @OneToMany(mappedBy = "menu")
    private Set<Meal> meals;

    @NotNull
    @ManyToOne(targetEntity = Restaurant.class)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Menu(int id, Restaurant restaurant, LocalDate date, Set<Meal> meals) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.meals = meals;
    }
}
