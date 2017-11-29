package com.amagi.account.pojo.request;

import com.amagi.account.util.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatus {

    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("end_time")
    private Timestamp reportedTime;
    @JsonProperty("activity_name")
    private String activityName;
    @JsonProperty("status_string")
    private String statusStr;
    @JsonProperty("status_number")
    private Integer statusNumber;


    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setReportedTime(Timestamp reportedTime) {
        this.reportedTime = reportedTime;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    @JsonIgnore
    public Timestamp getReportedTimestamp() {
        return reportedTime;
    }

    public String getReportedTime() {
        return Utils.getDateInUTCFormat(reportedTime.getTime());
    }

    public String getActivityName() {
        return activityName;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public Integer getStatusNumber() {
        return statusNumber;
    }
}
