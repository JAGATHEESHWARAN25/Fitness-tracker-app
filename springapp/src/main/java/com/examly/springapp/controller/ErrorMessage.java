package com.examly.springapp.controller;

import java.util.Map;

public class ErrorMessage {
    private String message;
    private Map<String, String> errors;

    public ErrorMessage() {}

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Map<String, String> getErrors() { return errors; }
    public void setErrors(Map<String, String> errors) { this.errors = errors; }
}
