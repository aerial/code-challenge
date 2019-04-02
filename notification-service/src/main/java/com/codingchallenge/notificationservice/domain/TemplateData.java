package com.codingchallenge.notificationservice.domain;

import com.codingchallenge.templateservice.api.TemplateDTO;
import lombok.Data;

@Data
public class TemplateData {
    private long id;
    private String name;
    private String content;

    public TemplateData(long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public TemplateData(TemplateDTO templateDTO) {
        if (templateDTO != null) {
            this.id = templateDTO.getId();
            this.name = templateDTO.getName();
            this.content = templateDTO.getContent();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
