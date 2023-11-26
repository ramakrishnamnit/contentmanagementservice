package com.project.content.contentmanagementservice.exception;

public class ContentManagementException extends RuntimeException{

    String message;
    public ContentManagementException (String message) {
        super(message);
        this.message = message;
    }
}
