package com.codingchallenge.notificationservice;

import com.codingchallenge.notificationservice.domain.Notification;
import com.codingchallenge.notificationservice.domain.NotificationService;
import com.codingchallenge.notificationservice.web.NotificationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest
public class NotificationServiceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NotificationService notificationService;

    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnWelcomeNotification() {
        when(notificationService.sendWelcomeLetter(1L)).thenReturn(Mono.just(new Notification("test")));
        webTestClient.get().uri("/notification/sendWelcome?userId=1").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Notification.class)
                .isEqualTo(new Notification("test"));
    }



    @Test
    public void shouldReturnMultipleNotifications() {
        Flux<Notification> notificationFlux = Flux.create(sink -> {
            sink.next(new Notification("test1"));
            sink.next(new Notification("test2"));
            sink.next(new Notification("test3"));
            sink.complete();
        });
        when(notificationService.sendNewsLetter()).thenReturn(notificationFlux);
        webTestClient.get().uri("/notification/sendNewsletter").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Notification.class)
                .isEqualTo(Arrays.asList(new Notification("test1"), new Notification("test2"),new Notification("test3")));
    }


}
