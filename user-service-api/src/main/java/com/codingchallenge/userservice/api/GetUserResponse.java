package com.codingchallenge.userservice.api;

public class GetUserResponse {

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    private UserDTO user;

    public GetUserResponse(UserDTO user) {
        this.user = user;
    }

    public GetUserResponse() {}

}
