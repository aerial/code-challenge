package com.codingchallenge.templateservice.data;

import com.codingchallenge.templateservice.domain.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {
    Optional<Template> findByName(String name);
}