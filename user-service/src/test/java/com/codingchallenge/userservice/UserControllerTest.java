package com.codingchallenge.userservice;

import com.codingchallenge.userservice.domain.User;
import com.codingchallenge.userservice.domain.service.UserService;
import com.codingchallenge.userservice.web.UserServiceController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserServiceController userServiceController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userServiceController).build();
    }

    @Test
    public void shouldReturnAUser() throws Exception {
        User user = new User(1, "TestSurname", "TestFirstName", "Test", "Test", false);
        when(userService.findById(1L)).thenReturn(Optional.of(user));
        this.mockMvc.perform(get("/users/1"))
                             .andDo(print())
                             .andExpect(status().isOk())
                             .andExpect(jsonPath("$.id").value(1))
                             .andExpect(jsonPath("$.sureName").value("TestSurname"))
                             .andExpect(jsonPath("$.firstName").value("TestFirstName"))
                             .andExpect(jsonPath("$.gender").value("Test"))
                             .andExpect(jsonPath("$.email").value("Test"))
                             .andExpect(jsonPath("$.subscribedNewsletter").value(false));
    }

    @Test
    public void shouldReturnUsers() throws Exception {
        List<User> users = Arrays.asList(new User(1, "TestSurname", "TestFirstName", "Test", "Test", false),
                                         new User(2, "TestSurname", "TestFirstName", "Test", "Test", false));
        when(userService.findAll()).thenReturn(users);
        this.mockMvc.perform(get("/users/all"))
                             .andDo(print())
                             .andExpect(status().isOk())
                             .andExpect(jsonPath("$", hasSize(2)))
                             .andExpect(jsonPath("$[0].id").value(1))
                             .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void shouldReturnOnlySubscribedUsers() throws Exception {
        List<User> users = Arrays.asList(new User(1, "TestSurname", "TestFirstName", "Test", "Test", true),
                new User(2, "TestSurname", "TestFirstName", "Test", "Test", true));
        when(userService.findSubscribedUsers()).thenReturn(users);
        this.mockMvc.perform(get("/users/subscribed"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].subscribedNewsletter").value(true))
                .andExpect(jsonPath("$[1].subscribedNewsletter").value(true));
    }

}
