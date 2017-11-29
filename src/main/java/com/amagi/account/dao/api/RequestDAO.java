package com.amagi.account.dao.api;

import com.amagi.account.pojo.request.JobStatus;
import com.amagi.account.pojo.request.RequestStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RequestDAO {

    Boolean saveRequestStatus(RequestStatus requestStatus);
    Boolean saveJobStatus(JobStatus jobStatus);
    List<RequestStatus> getRequestStatuses(String requestId);
    List<JobStatus> getJobStatuses(String requestId);
}
