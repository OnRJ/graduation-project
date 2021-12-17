package com.dailymenurating.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString(exclude = {"user"})
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
