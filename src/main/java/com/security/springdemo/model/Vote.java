package com.security.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@NamedQueries({
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v WHERE v.date=:date"),
        @NamedQuery(name = Vote.GET_BY_USER_AND_DATE, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date"),
        @NamedQuery(name = Vote.GET_BY_RESTAURANT_AND_DATE, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.date=:date"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id")
})
@Entity
@Table(name = "vote")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vote extends AbstractBaseEntity {

    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_BY_USER_AND_DATE = "Vote.getByUserIdAndDate";
    public static final String GET_BY_RESTAURANT_AND_DATE = "Vote.getByRestaurantIdAndDate";
    public static final String DELETE = "Vote.delete";

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Restaurant.class)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "datetime", nullable = false)
    @NotNull
    private LocalDate date;

    public Vote() {
    }

    public Vote(int id, LocalDate date, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", user=" + user +
                '}';
    }
}
