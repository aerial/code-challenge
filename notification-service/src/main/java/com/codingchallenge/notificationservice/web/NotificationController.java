package com.codingchallenge.notificationservice.web;

import com.codingchallenge.notificationservice.domain.Notification;
import com.codingchallenge.notificationservice.domain.NotificationService;
import com.codingchallenge.notificationservice.proxy.TemplateServiceProxy;
import com.codingchallenge.notificationservice.proxy.UserServiceProxy;
import com.codingchallenge.templateservice.api.TemplateDTO;
import com.codingchallenge.userservice.api.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping(value = "/notification/sendWelcome", method = RequestMethod.POST)
    public Mono<Notification> sendWelcomeLetter(@RequestParam("userId") Long userId) {
        return notificationService.sendWelcomeLetter(userId);
    }

    @RequestMapping(value = "/notification/sendNewsletter", method = RequestMethod.POST)
    public Flux<Notification> sendNewsLetter() {
        return notificationService.sendNewsLetter();
    }

}
