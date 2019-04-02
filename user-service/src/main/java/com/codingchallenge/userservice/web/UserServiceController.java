package com.codingchallenge.userservice.web;

import com.codingchallenge.userservice.api.UserDTO;
import com.codingchallenge.userservice.domain.User;
import com.codingchallenge.userservice.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserServiceController {

    private UserService userService;

    @Autowired
    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users/subscribed")
    public ResponseEntity<List<UserDTO>> getSubscribedUsers() {
        List<UserDTO> users = userService.findSubscribedUsers().stream().map(this::fromUser).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.findAll().stream().map(this::fromUser).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        return userService.findById(id)
                .map(user -> new ResponseEntity<>(fromUser(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private UserDTO fromUser(User user) {
        return new UserDTO(user.getId(),
                user.getSureName(),
                user.getFirstName(),
                user.getGender(),
                user.getEmail(),
                user.isSubscribedNewsletter());
    }
}
