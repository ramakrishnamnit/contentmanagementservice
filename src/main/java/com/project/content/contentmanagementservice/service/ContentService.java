package com.project.content.contentmanagementservice.service;

import com.project.content.contentmanagementservice.model.Content;

import java.util.List;

public interface ContentService {

    String saveOrUpdateContent(Content content);

    Content getContentById(String id);

    List<Content> getContents();
}
