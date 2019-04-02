package com.codingchallenge.userservice;

import com.codingchallenge.userservice.domain.User;
import com.codingchallenge.userservice.domain.service.UserService;
import com.codingchallenge.userservice.web.UserServiceController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class BaseMvcTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserServiceController templateController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(templateController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
        User user = new User(1, "Turner", "Tom", "male", "tom.turner@provider.de", true);
        when(userService.findById(1L)).thenReturn(Optional.of(user));
    }
}
