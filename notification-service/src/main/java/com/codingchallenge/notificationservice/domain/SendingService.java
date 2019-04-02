package com.codingchallenge.notificationservice.domain;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log
public class SendingService {

    private final static Integer MAX_BATCH_SIZE = 10;

    public void sendNotification(Mono<Notification> notification) {
        log.info("Sending message to imaginary service");
    }

    public void sendNotifications(Flux<Notification> notifications) {
        notifications.buffer(MAX_BATCH_SIZE).subscribe(batch -> {
            log.info("Sending a batch of " + batch.size() + " messages to imaginary service");
        });
    }
}
