package com.project.content.contentmanagementservice.model;

import lombok.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Content {
    private String contentId; // Auto-generated or set when updating
    private String templateId;
    private String authorId;
    private long createdAt; // Epoch time
    private long updatedAt; // Epoch time
    private Map<String, Object> contentData;
}

