package com.amagi.account.pojo.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerTimeZone {

    private String value;
    private String abbrv;
    private String offset;
    private Boolean isDst;
    private String text;
    private List<String> utc;

    public void setValue(String value) {
        this.value = value;
    }

    public void setAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public void setDst(Boolean dst) {
        isDst = dst;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUtc(List<String> utc) {
        this.utc = utc;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }
    @JsonProperty("abbrv")
    public String getAbbrv() {
        return abbrv;
    }
    @JsonProperty("offset")
    public String getOffset() {
        return offset;
    }
    @JsonProperty("isdst")
    public Boolean isDst() {
        return isDst;
    }
    @JsonProperty("text")
    public String getText() {
        return text;
    }
    @JsonProperty("utc")
    public List<String> getUtc() {
        return utc;
    }
}
