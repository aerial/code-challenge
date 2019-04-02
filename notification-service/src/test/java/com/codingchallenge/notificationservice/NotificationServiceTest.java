package com.codingchallenge.notificationservice;

import com.codingchallenge.notificationservice.domain.Notification;
import com.codingchallenge.notificationservice.domain.NotificationService;
import com.codingchallenge.notificationservice.domain.SendingService;
import com.codingchallenge.notificationservice.domain.UserData;
import com.codingchallenge.notificationservice.processor.UserTemplateProcessor;
import com.codingchallenge.notificationservice.proxy.TemplateServiceProxy;
import com.codingchallenge.notificationservice.proxy.UserServiceProxy;
import com.codingchallenge.templateservice.api.TemplateDTO;
import com.codingchallenge.userservice.api.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private UserServiceProxy userServiceProxy;

    @Mock
    private TemplateServiceProxy templateServiceProxy;

    @Mock
    private UserTemplateProcessor userTemplateProcessor;

    @Mock
    private SendingService sendingService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateWelcomeLetter() {
        UserDTO user = new UserDTO(1,  "TestSurname", "TestFirstName", "Test", "Test", false);
        TemplateDTO template = new TemplateDTO(1, "templateWelcome", "{{user.firstName}} {{user.surName}}");
        when(userServiceProxy.getUser(1L)).thenReturn(Mono.just(user));
        when(templateServiceProxy.getTemplateByName("templateWelcome")).thenReturn(Mono.just(template));
        when(userTemplateProcessor.process(any(), any())).thenReturn(new Notification("TestFirstName TestSurname"));
        Notification expected = new Notification("TestFirstName TestSurname");
        StepVerifier.create(notificationService.sendWelcomeLetter(1L))
                .expectNext(expected)
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldCreateNewsletter() {
        UserDTO userOne = new UserDTO(1,  "TestSurname", "TestFirstName", "Test", "Test", false);
        UserDTO userTwo = new UserDTO(2,  "TestSurname2", "TestFirstName2", "Test", "Test", false);
        Flux<UserDTO> users = Flux.just(userOne, userTwo);
        TemplateDTO template = new TemplateDTO(2, "templateNewsletter", "{{user.firstName}} {{user.surName}}");
        when(userServiceProxy.getSubscribedUsers()).thenReturn(users);
        when(templateServiceProxy.getTemplateByName("templateNewsletter")).thenReturn(Mono.just(template));
        when(userTemplateProcessor.process(any(), eq(new UserData(userOne)))).thenReturn(new Notification("TestFirstName TestSurname"));
        when(userTemplateProcessor.process(any(), eq(new UserData(userTwo)))).thenReturn(new Notification("TestFirstName2 TestSurname2"));
        Notification expectedOne = new Notification("TestFirstName TestSurname");
        Notification expectedTwo = new Notification("TestFirstName2 TestSurname2");
        StepVerifier.create(notificationService.sendNewsLetter())
                .expectNext(expectedOne)
                .expectNext(expectedTwo)
                .expectComplete()
                .verify();
    }

}
