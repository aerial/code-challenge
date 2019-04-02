package com.codingchallenge.notificationservice.proxy;

import com.codingchallenge.templateservice.api.TemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TemplateServiceProxy {

    private static final String PROTOCOL_PREFIX = "http://";
    private static final String TEMPLATE_SERVICE_URI = PROTOCOL_PREFIX.concat("template-service/templates");
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public TemplateServiceProxy(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<TemplateDTO> getTemplateById(long id) {
        return webClientBuilder.build().get().uri(TEMPLATE_SERVICE_URI.concat("/{id}"), id)
                .retrieve()
                .bodyToMono(TemplateDTO.class);
    }

    public Mono<TemplateDTO> getTemplateByName(String name) {
        return webClientBuilder.build().get().uri(TEMPLATE_SERVICE_URI.concat("/filter?name=").concat(name))
                .retrieve()
                .bodyToMono(TemplateDTO.class);
    }
}
