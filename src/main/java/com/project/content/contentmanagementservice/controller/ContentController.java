package com.project.content.contentmanagementservice.controller;


import com.project.content.contentmanagementservice.model.Content;
import com.project.content.contentmanagementservice.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // Endpoint to save new content or update existing content
    @PostMapping
    public ResponseEntity<String> saveOrUpdateContent(@RequestBody Content content) {
            String savedContentId = contentService.saveOrUpdateContent(content);
            return ResponseEntity.ok("Content saved with ID: " + savedContentId);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Content> getContentsById(@RequestParam(required = false) @PathVariable("contentId") String contentId) {
        return ResponseEntity.ok(contentService.getContentById(contentId));
    }

    @GetMapping
    public ResponseEntity<List<Content>> getContents() {
        return ResponseEntity.ok(contentService.getContents());
    }
}
