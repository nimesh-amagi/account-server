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
public class RequestStatus {

    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("deployment_id")
    private String deploymentId;
    @JsonProperty("api_end_point")
    private String apiEndPoint;
    @JsonProperty("status_number")
    private Integer statusNumber;
    @JsonProperty("total_tasks")
    private Integer totalTasks;
    @JsonProperty("status_string")
    private String statusString;
    @JsonProperty("reported_time")
    private Timestamp reportedTime;
    @JsonProperty("is_channel_request")
    private Boolean isChannelRequest;

    public String getRequestId() {
        return requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public String getApiEndPoint() {
        return apiEndPoint;
    }

    public Integer getStatusNumber() {
        return statusNumber;
    }

    public Integer getTotalTasks() {
        return totalTasks;
    }

    public String getStatusString() {
        return statusString;
    }

    @JsonIgnore
    public Timestamp getReportedTimeTimeStamp() {
        return reportedTime;
    }

    public String getReportedTime() {
        return Utils.getDateInUTCFormat(reportedTime.getTime());
    }

    public Boolean isChannelRequest() {
        return isChannelRequest;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public void setApiEndPoint(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    public void setStatusNumber(Integer statusNumber) {
        this.statusNumber = statusNumber;
    }

    public void setTotalTasks(Integer totalTasks) {
        this.totalTasks = totalTasks;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public void setReportedTime(Timestamp reportedTime) {
        this.reportedTime = reportedTime;
    }

    public void setChannelRequest(Boolean channelRequest) {
        isChannelRequest = channelRequest;
    }
}
