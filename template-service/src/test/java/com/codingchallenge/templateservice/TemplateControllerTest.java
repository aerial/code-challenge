package com.codingchallenge.templateservice;

import com.codingchallenge.templateservice.domain.Template;
import com.codingchallenge.templateservice.domain.TemplateService;
import com.codingchallenge.templateservice.web.TemplateController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TemplateControllerTest {

    @Mock
    private TemplateService templateService;

    @InjectMocks
    private TemplateController templateController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(templateController).build();
    }

    @Test
    public void shouldReturnTemplateById() throws Exception {
        Template template = new Template(1, "TestName", "TestContent");
        when(templateService.findById(1L)).thenReturn(Optional.of(template));
        this.mockMvc.perform(get("/templates/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.content").value("TestContent"));
    }

    @Test
    public void shouldReturnTemplateByName() throws Exception {
        Template template = new Template(1, "TestName", "TestContent");
        when(templateService.findByName("TestName")).thenReturn(Optional.of(template));
        this.mockMvc.perform(get("/templates/filter?name=TestName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.content").value("TestContent"));
    }
}
