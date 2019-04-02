package com.codingchallenge.notificationservice;

import com.codingchallenge.notificationservice.domain.Notification;
import com.codingchallenge.notificationservice.domain.TemplateData;
import com.codingchallenge.notificationservice.domain.UserData;
import com.codingchallenge.notificationservice.processor.UserTemplateProcessor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTemplateProcessorTest {

    private UserTemplateProcessor userTemplateProcessor = new UserTemplateProcessor();

    @Test
    public void shouldProcessATemplate() {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setFirstName("TestFirstName");
        userData.setSurName("TestSurname");
        String template = "1: {{user.id}} 2: {{user.firstName}} 3: {{user.surName}}, 4: {{user.none}}, 5 :{{some.key}}";
        String expected = "1: 1 2: TestFirstName 3: TestSurname, 4: {{user.none}}, 5 :{{some.key}}";
        TemplateData templateData = new TemplateData(1L, "templateName" ,template);
        Notification notification = userTemplateProcessor.process(templateData, userData);
        assertEquals(notification.getContent(), expected);
    }
}
