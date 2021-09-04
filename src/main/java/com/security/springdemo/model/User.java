package com.security.springdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "registered")
    @JsonIgnore
    private String registered;

    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @JsonIgnore
    private Role role;
}
