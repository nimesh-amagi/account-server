package com.amagi.account.service.api;

import com.amagi.account.pojo.request.JobStatus;
import com.amagi.account.pojo.request.RequestDescription;
import com.amagi.account.pojo.request.RequestStatus;
import org.springframework.stereotype.Service;

@Service
public interface RequestManager {

    Boolean saveRequest(RequestStatus requestStatus);
    Boolean saveJobStatus(JobStatus jobStatus);
    RequestDescription getRequestDescription(String requestId);
}
