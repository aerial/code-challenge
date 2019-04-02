package com.codingchallenge.userservice.domain.service;

import com.codingchallenge.userservice.data.UserRepository;
import com.codingchallenge.userservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> findSubscribedUsers() {
        return userRepository.findBySubscribedNewsletter(true);
    }

}
