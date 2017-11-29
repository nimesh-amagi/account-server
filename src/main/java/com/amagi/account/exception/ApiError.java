package com.amagi.account.exception;


import java.util.Arrays;
import java.util.List;

public class ApiError {

    private Integer status = -1;
    private List<String> messages;
    private List<String> errors;
    private Object data = null;

    public ApiError() {
        super();
    }

    public ApiError(final List<String> messages, final List<String> errors) {
        super();
        this.messages = messages;
        this.errors = errors;
    }

    public ApiError(final String message, final String error) {
        super();
        messages = Arrays.asList(message);
        errors = Arrays.asList(error);
    }

    public Integer getStatus() {
        return status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(final List<String> messages) {
        this.messages = messages;
    }

    public void setMessage(final String message) {
        messages = Arrays.asList(message);;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

    public Object getData() {
        return data;
    }

}