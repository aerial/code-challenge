package com.codingchallenge.templateservice.domain;

import com.codingchallenge.templateservice.data.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateService(TemplateRepository repository) {
        this.templateRepository = repository;
    }

    public Optional<Template> findById(long id) {
        return templateRepository.findById(id);
    }

    public Optional<Template> findByName(String name) {
        return templateRepository.findByName(name);
    }
}
