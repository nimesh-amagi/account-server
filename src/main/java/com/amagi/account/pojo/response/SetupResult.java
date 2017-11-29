package com.amagi.account.pojo.response;

public class SetupResult {

    private Exception exception;
    private SetupStatus status;
    private String message;

    public SetupResult(Exception exception, SetupStatus status, String message) {
        this.exception = exception;
        this.status = status;
        this.message = message;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setStatus(SetupStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public SetupStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static enum SetupStatus {
        SUCCESS,
        FAILED;
    }
}
