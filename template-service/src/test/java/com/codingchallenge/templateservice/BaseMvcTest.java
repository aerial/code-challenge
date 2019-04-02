package com.codingchallenge.templateservice;

import com.codingchallenge.templateservice.domain.Template;
import com.codingchallenge.templateservice.domain.TemplateService;
import com.codingchallenge.templateservice.web.TemplateController;
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
    private TemplateService templateService;

    @InjectMocks
    private TemplateController templateController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(templateController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
        Template template = new Template(1, "TestName", "TestContent");
        when(templateService.findById(1L)).thenReturn(Optional.of(template));
        when(templateService.findByName("TestName")).thenReturn(Optional.of(template));
    }
}
