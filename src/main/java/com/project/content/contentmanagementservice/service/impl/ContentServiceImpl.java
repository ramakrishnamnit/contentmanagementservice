package com.project.content.contentmanagementservice.service.impl;

import com.project.content.contentmanagementservice.dao.GenericFireBaseDAO;
import com.project.content.contentmanagementservice.exception.ContentManagementException;
import com.project.content.contentmanagementservice.model.Content;
import com.project.content.contentmanagementservice.service.ContentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ContentServiceImpl implements ContentService {

    private final Logger logger = LogManager.getLogger(ContentServiceImpl.class);

    @Autowired
    GenericFireBaseDAO genericFireBaseDAO;

    @Override
    public String saveOrUpdateContent(Content content) {
        if (content.getContentId() == null) {
            String id = UUID.randomUUID().toString();
            content.setContentId(id);
            content.setCreatedAt(System.currentTimeMillis());
            genericFireBaseDAO.save("CONTENT_STORE",id,content);

        } else {
          content.setUpdatedAt(System.currentTimeMillis());
            genericFireBaseDAO.save("CONTENT_STORE",content.getContentId(),content);
        }
        return content.getContentId();
    }

    @Override
    public Content getContentById(String id) {
        try {
            return genericFireBaseDAO.getById(Content.class,"CONTENT_STORE",id);
        } catch(Exception e){
            logger.error("Failed to get template");
            throw new ContentManagementException("Failed to get template");
        }
    }

    @Override
    public List<Content> getContents() {
        try {
            return genericFireBaseDAO.getAll(Content.class,"CONTENT_STORE");
        } catch(Exception e){
            logger.error("Failed to get templates");
            throw new ContentManagementException("Failed to get templates");
        }
    }
}
