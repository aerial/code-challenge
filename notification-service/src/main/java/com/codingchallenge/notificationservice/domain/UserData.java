package com.codingchallenge.notificationservice.domain;

import com.codingchallenge.userservice.api.UserDTO;
import lombok.Data;

@Data
public class UserData {
    private long id;
    private String surName;
    private String firstName;
    private String gender;
    private String email;
    private boolean subscribedNewsletter;

    public UserData() {}

    public UserData(UserDTO userDTO) {
        if (userDTO != null) {
            this.id = userDTO.getId();
            this.surName = userDTO.getSureName();
            this.firstName = userDTO.getSureName();
            this.gender = userDTO.getGender();
            this.email = userDTO.getEmail();
            this.subscribedNewsletter = userDTO.isSubscribedNewsletter();
        }
    }
}
