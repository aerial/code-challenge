package com.codingchallenge.userservice;

import com.codingchallenge.userservice.data.UserRepository;
import com.codingchallenge.userservice.domain.User;
import com.codingchallenge.userservice.domain.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAUser() {
        User user = new User(1, "Test", "Test", "Test", "Test",false);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> returned = userService.findById(1L);
        verify(userRepository).findById(1L);
        assertEquals(user, returned.get());
    }

    @Test
    public void shouldReturnUsers() {
        List<User> users = Arrays.asList(new User(1, "Test", "Test", "Test", "Test",false),
                new User(1, "Test", "Test", "Test", "Test",false));
        when(userRepository.findAll()).thenReturn(users);
        List<User> returned = userService.findAll();
        verify(userRepository).findAll();
        assertEquals(users, returned);
    }

    @Test
    public void shouldReturnSubscribedUsers() {
        List<User> users = Arrays.asList(new User(1, "Test", "Test", "Test", "Test",true),
                new User(2, "Test", "Test", "Test", "Test",true));
        when(userRepository.findBySubscribedNewsletter(true)).thenReturn(users);
        List<User> returned = userService.findSubscribedUsers();
        verify(userRepository).findBySubscribedNewsletter(true);
        assertEquals(users, returned);
    }

}
