package com.codingchallenge.templateservice;

import com.codingchallenge.templateservice.data.TemplateRepository;
import com.codingchallenge.templateservice.domain.Template;
import com.codingchallenge.templateservice.domain.TemplateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TemplateServiceTest {

    @Mock
    private TemplateRepository templateRepository;

    @InjectMocks
    private TemplateService templateService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTemplateByName() {
        Template template = new Template(1, "TestName", "TestContent");
        when(templateRepository.findByName("TestName")).thenReturn(Optional.of(template));
        Optional<Template> returned = templateService.findByName("TestName");
        verify(templateRepository).findByName("TestName");
        assertEquals(template, returned.get());
    }

    @Test
    public void shouldReturnTemplateById() {
        Template template = new Template(1, "TestName", "TestContent");
        when(templateRepository.findById(1L)).thenReturn(Optional.of(template));
        Optional<Template> returned = templateService.findById(1L);
        verify(templateRepository).findById(1L);
        assertEquals(template, returned.get());
    }
}
