package com.project.content.contentmanagementservice.service;

import com.project.content.contentmanagementservice.model.Template;

import java.util.List;

public interface TemplateService {
    public String saveTemplate(Template template);

    Template getTemplateById(String id);

    List<Template> getTemplates();

}
