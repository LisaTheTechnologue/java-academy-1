package com.example.SpringMVCDemo;

public class ErrorModel {
    private String source;
    private String destination;
    private String message;
    private String stackTrace;

    public ErrorModel(String source, String destination, String message, String stackTrace) {
        this.source = source;
        this.destination = destination;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
