package com.security.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
@Entity
@Table(name = "restaurant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant extends AbstractNamedEntity {

    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
