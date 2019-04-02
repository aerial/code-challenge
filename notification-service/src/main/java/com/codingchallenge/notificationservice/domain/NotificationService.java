package com.codingchallenge.notificationservice.domain;

import com.codingchallenge.notificationservice.processor.UserTemplateProcessor;
import com.codingchallenge.notificationservice.proxy.TemplateServiceProxy;
import com.codingchallenge.notificationservice.proxy.UserServiceProxy;
import com.codingchallenge.templateservice.api.TemplateDTO;
import com.codingchallenge.userservice.api.UserDTO;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

    private final UserServiceProxy userServiceProxy;

    private final TemplateServiceProxy templateServiceProxy;

    private final UserTemplateProcessor userTemplateProcessor;

    private final SendingService sendingService;

    @Autowired
    public NotificationService(UserServiceProxy userServiceProxy,
                               TemplateServiceProxy templateServiceProxy,
                               UserTemplateProcessor userTemplateProcessor,
                               SendingService sendingService) {
        this.userServiceProxy = userServiceProxy;
        this.templateServiceProxy = templateServiceProxy;
        this.userTemplateProcessor = userTemplateProcessor;
        this.sendingService = sendingService;
    }

    public Mono<Notification> sendWelcomeLetter(long userId) {
        Mono<UserDTO> user = userServiceProxy.getUser(userId);
        return prepareNotification(user, fetchTemplate("templateWelcome"));
    }

    public Flux<Notification> sendNewsLetter() {
        Flux<UserDTO> users = userServiceProxy.getSubscribedUsers();
        return prepareNotifications(users, fetchTemplate("templateNewsletter"));
    }

    private Mono<Notification> prepareNotification(Mono<UserDTO> user, Mono<TemplateDTO> template) {
        Mono<Notification> notification = user.zipWith(template).map(t -> {
                    UserData userData = new UserData(t.getT1());
                    TemplateData templateData = new TemplateData(t.getT2());
                    return userTemplateProcessor.process(templateData, userData);
                });
        sendingService.sendNotification(notification);
        return notification;
    }

    private Flux<Notification> prepareNotifications(Flux<UserDTO> users, Mono<TemplateDTO> template) {
        Flux<Notification> notifications = users.flatMap(user -> Mono.just(user).zipWith(template).map(t -> {
            UserData userData = new UserData(t.getT1());
            TemplateData templateData = new TemplateData(t.getT2());
            return userTemplateProcessor.process(templateData, userData);
        }));
        sendingService.sendNotifications(notifications);
        return notifications;

    }

    private Mono<TemplateDTO> fetchTemplate(String templateName) {
        return templateServiceProxy.getTemplateByName(templateName);
    }
}

