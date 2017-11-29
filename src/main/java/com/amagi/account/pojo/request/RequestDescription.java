package com.amagi.account.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestDescription {

    private Double percentageCompletion;
    private RequestStatus startRequestStatus;
    private RequestStatus endRequestStatus;
    private List<JobStatus> jobStatuses;

    public void setPercentageCompletion(Double percentageCompletion) {
        this.percentageCompletion = percentageCompletion;
    }

    public void setStartRequestStatus(RequestStatus startRequestStatus) {
        this.startRequestStatus = startRequestStatus;
    }

    public void setEndRequestStatus(RequestStatus endRequestStatus) {
        this.endRequestStatus = endRequestStatus;
    }

    public void setJobStatuses(List<JobStatus> jobStatuses) {
        this.jobStatuses = jobStatuses;
    }
    @JsonProperty("percentage_completion")
    public Double getPercentageCompletion() {
        return percentageCompletion;
    }
    @JsonProperty("start_request_status")
    public RequestStatus getStartRequestStatus() {
        return startRequestStatus;
    }
    @JsonProperty("end_request_status")
    public RequestStatus getEndRequestStatus() {
        return endRequestStatus;
    }
    @JsonProperty("jobs")
    public List<JobStatus> getJobStatuses() {
        return jobStatuses;
    }
}
