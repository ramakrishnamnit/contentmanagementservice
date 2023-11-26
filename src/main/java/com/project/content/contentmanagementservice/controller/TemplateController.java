package com.project.content.contentmanagementservice.controller;

import com.project.content.contentmanagementservice.model.Template;
import com.project.content.contentmanagementservice.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/templates") // API versioning added here
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping
    public ResponseEntity<Object> saveTemplate(@RequestBody Template template) {
        try {
            String templateId = templateService.saveTemplate(template);
            return ResponseEntity.ok().body(Map.of("templateId", templateId, "message", "Template saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving template: " + e.getMessage());
        }
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<Template> getTemplateById(@RequestParam(required = false) @PathVariable("templateId") String templateId) {
        return ResponseEntity.ok(templateService.getTemplateById(templateId));
    }

    @GetMapping
    public ResponseEntity<List<Template>> getTemplates() {
        List<Template> templates = templateService.getTemplates();
        return ResponseEntity.ok(templates);
    }

}

