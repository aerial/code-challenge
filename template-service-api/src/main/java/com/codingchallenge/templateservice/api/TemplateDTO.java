package com.codingchallenge.templateservice.api;

public class TemplateDTO {

    private long id;
    private String name;
    private String content;

    public TemplateDTO(long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public TemplateDTO() {}

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
