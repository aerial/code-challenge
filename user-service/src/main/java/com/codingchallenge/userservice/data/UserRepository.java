package com.codingchallenge.userservice.data;

import com.codingchallenge.userservice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findBySubscribedNewsletter(Boolean subscribedNewsletter);
}
