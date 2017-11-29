package com.amagi.account.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class will serve as an uniform response template for all REST request.
 * Payload can be added as List, Map or Class Object.
 * Spring framework will convert this payload in suitable JSON output using Jackson JSON lib.
 *
 * As there are few ways to populate data payload, instead of constructor overloading, Builder is provided.
 * e.g.
 *  Response resposne = new Response.Builder().setStatus(200).setOneMessage("success").build();
 */
public final class Response <T> {
    private final int status;
    private final List<String> error;
    private final List<String> message;
    private final List<T> dataList;
    private final Map<String, T> dataMap;
    private final Object dataObj;

    private Response(int status, List<String> error, List<String> message, List<T> dataList, Map<String, T> dataMap, Object dataObj) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.dataList = dataList;
        this.dataMap = dataMap;
        this.dataObj = dataObj;
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("errors")
    public List<String> getError() {
        return error;
    }

    @JsonProperty("messages")
    public List<String> getMessage() {
        return message;
    }

    @JsonProperty("data")
    public Object getData() {
        if (!dataList.isEmpty()) {
            return dataList;
        }
        if (dataObj != null) {
            return dataObj;
        }
        return dataMap;
    }

    public static class Builder <T> {
        private int status;
        private List<String> errors;
        private List<String> messages;
        private List<? extends Object> dataList;
        private Map<String, T> dataMap;
        private Object dataObj;

        public Builder() {
            this.status = 1;
            this.messages = new ArrayList<>();
            this.errors = new ArrayList<>();
            this.dataList = Collections.emptyList();
            this.dataMap = Collections.emptyMap();
            this.dataObj = null;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setOneError(String error) {
            this.errors.add(error);
            return this;
        }

        public Builder setErrors(List<String> errors) {
            this.errors.addAll(errors);
            return this;
        }

        public Builder setOneMessage(String message) {
            this.messages.add(message);
            return this;
        }

        public Builder setMessages(List<String> messages) {
            this.messages.addAll(messages);
            return this;
        }

        public <T> Builder setDataAsList(List<T> list) {
            this.dataList = list;
            return this;
        }

        public Builder setDataAsMap(Map<String, T> map) {
            this.dataMap = map;
            return this;
        }

        public Builder setDataAsClassObject(Object object) {
            this.dataObj = object;
            return this;
        }

        public Response build() {
            return new Response(this.status, this.errors, this.messages, this.dataList, this.dataMap, this.dataObj);
        }
    }
}
