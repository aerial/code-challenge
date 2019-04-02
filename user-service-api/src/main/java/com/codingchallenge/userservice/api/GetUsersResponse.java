package com.codingchallenge.userservice.api;

import java.util.ArrayList;
import java.util.List;

public class GetUsersResponse {

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    private List<UserDTO> users = new ArrayList<>();

    public GetUsersResponse(List<UserDTO> users) {
        this.users = users;
    }

    public GetUsersResponse() {}
}
