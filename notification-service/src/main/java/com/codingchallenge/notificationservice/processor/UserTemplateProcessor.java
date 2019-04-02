package com.codingchallenge.notificationservice.processor;

import com.codingchallenge.notificationservice.domain.Notification;
import com.codingchallenge.notificationservice.domain.TemplateData;
import com.codingchallenge.notificationservice.domain.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserTemplateProcessor {

    public Notification process(TemplateData template, UserData target) {
        Map<String, Object> map = new ObjectMapper().convertValue(target, Map.class);
        String enrichedTemplate = StrSubstitutor.replace(template.getContent(), map, "{{user.", "}}");
        return new Notification(enrichedTemplate);
    }
}
