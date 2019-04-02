package com.codingchallenge.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "SURNAME")
    private String sureName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    private String gender;
    private String email;
    @Column(name = "SUBSCRIBED")
    private boolean subscribedNewsletter;
}
