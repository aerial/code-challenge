package com.codingchallenge.notificationservice.proxy;

import com.codingchallenge.userservice.api.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceProxy {

    private static final String PROTOCOL_PREFIX = "http://";
    private static final String USER_SERVICE_URI = PROTOCOL_PREFIX.concat("user-service/users");
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public UserServiceProxy(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<UserDTO> getUser(long id) {
        return webClientBuilder.build().get().uri(USER_SERVICE_URI.concat("/{id}"), id)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Flux<UserDTO> getUsers() {
        return webClientBuilder.build().get().uri(USER_SERVICE_URI.concat("/all"))
                .retrieve()
                .bodyToFlux(UserDTO.class);
    }

    public Flux<UserDTO> getSubscribedUsers() {
        return webClientBuilder.build().get().uri(USER_SERVICE_URI.concat("/subscribed"))
                .retrieve()
                .bodyToFlux(UserDTO.class);
    }
}
