package com.codingchallenge.templateservice.web;

import com.codingchallenge.templateservice.api.TemplateDTO;
import com.codingchallenge.templateservice.domain.Template;
import com.codingchallenge.templateservice.domain.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    private final TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @RequestMapping("/templates/{id}")
    public ResponseEntity<TemplateDTO> getById(@PathVariable long id) {
        return templateService.findById(id)
                .map(this::fromTemplate)
                .map(template -> new ResponseEntity<>(template, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/templates/filter")
    public ResponseEntity<TemplateDTO> getByName(@RequestParam("name") String name) {
        return templateService.findByName(name)
                .map(this::fromTemplate)
                .map(template -> new ResponseEntity<>(template, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private TemplateDTO fromTemplate(Template template) {
        return new TemplateDTO(template.getId(), template.getName(), template.getContent());
    }
}
