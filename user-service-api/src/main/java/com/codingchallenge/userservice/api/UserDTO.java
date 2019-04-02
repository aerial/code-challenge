package com.codingchallenge.userservice.api;

import java.util.Objects;

public class UserDTO {
    private long id;
    private String sureName;
    private String firstName;
    private String gender;
    private String email;
    private boolean subscribedNewsletter;

    public UserDTO() {}

    public UserDTO(long id, String sureName, String firstName, String gender, String email, boolean subscribedNewsletter) {
        this.id = id;
        this.sureName = sureName;
        this.firstName = firstName;
        this.gender = gender;
        this.email = email;
        this.subscribedNewsletter = subscribedNewsletter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscribedNewsletter() {
        return subscribedNewsletter;
    }

    public void setSubscribedNewsletter(boolean subscribedNewsletter) {
        this.subscribedNewsletter = subscribedNewsletter;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", sureName='" + sureName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", subscribedNewsletter=" + subscribedNewsletter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id &&
                subscribedNewsletter == userDTO.subscribedNewsletter &&
                Objects.equals(sureName, userDTO.sureName) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(gender, userDTO.gender) &&
                Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sureName, firstName, gender, email, subscribedNewsletter);
    }
}
