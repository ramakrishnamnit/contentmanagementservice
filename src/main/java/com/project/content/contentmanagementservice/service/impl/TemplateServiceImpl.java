package com.project.content.contentmanagementservice.service.impl;

import com.project.content.contentmanagementservice.dao.GenericFireBaseDAO;
import com.project.content.contentmanagementservice.exception.ContentManagementException;
import com.project.content.contentmanagementservice.model.Template;
import com.project.content.contentmanagementservice.service.TemplateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final Logger logger = LogManager.getLogger(TemplateServiceImpl.class);

    @Autowired
    GenericFireBaseDAO genericFireBaseDAO;

    @Override
    public String saveTemplate(Template template) {
        try {
            logger.info("saving template");
            String id = UUID.randomUUID().toString();
            long currentTime = System.currentTimeMillis();
            template.setCreatedAt(currentTime);
            template.setId(id);
            genericFireBaseDAO.save("TEMPLATE_STORE", id, template);
            return id;
        } catch (Exception e) {
            logger.error("[ITRSERROR] failed to save template with exception", e.getMessage());
            throw new ContentManagementException("Failed to save template");
        }
    }

    @Override
    public Template getTemplateById(String id) {
        try {
            return genericFireBaseDAO.getById(Template.class, "TEMPLATE_STORE", id);
        } catch (Exception e) {
            logger.error("Failed to get template");
            throw new ContentManagementException("Failed to get template");
        }
    }

    @Override
    public List<Template> getTemplates() {
        try {
            return genericFireBaseDAO.getAll(Template.class, "TEMPLATE_STORE");
        } catch (Exception e) {
            logger.error("Failed to get templates");
            throw new ContentManagementException("Failed to get templates");
        }
    }


}
