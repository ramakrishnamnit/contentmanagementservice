package com.project.content.contentmanagementservice.exception.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}