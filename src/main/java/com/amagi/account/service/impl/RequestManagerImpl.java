package com.amagi.account.service.impl;

import com.amagi.account.dao.api.CustomerDAO;
import com.amagi.account.dao.api.RequestDAO;
import com.amagi.account.pojo.request.JobStatus;
import com.amagi.account.pojo.request.RequestDescription;
import com.amagi.account.pojo.request.RequestStatus;
import com.amagi.account.service.api.RequestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestManagerImpl implements RequestManager {

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Boolean saveRequest(RequestStatus requestStatus) {
        Boolean status = requestDAO.saveRequestStatus(requestStatus);
        if (!requestStatus.isChannelRequest()) {
            customerDAO.updateRequestIdForCustomer(requestStatus.getCustomerId(), requestStatus.getRequestId());
        }
        return status;
    }

    @Override
    public Boolean saveJobStatus(JobStatus jobStatus) {
        return requestDAO.saveJobStatus(jobStatus);
    }

    @Override
    public RequestDescription getRequestDescription(String requestId) {
        List<RequestStatus> requestStatuses = requestDAO.getRequestStatuses(requestId);
        RequestDescription requestDescription = new RequestDescription();
        if (!requestStatuses.isEmpty()) {
            requestDescription.setStartRequestStatus(requestStatuses
                    .stream()
                    .filter(requestStatus -> requestStatus.getStatusNumber() == 0)
                    .findFirst()
                    .orElse(null));
            requestDescription.setEndRequestStatus(requestStatuses
                    .stream()
                    .filter(requestStatus -> requestStatus.getStatusNumber() != 0)
                    .findFirst()
                    .orElse(null));
            List<JobStatus> jobs = requestDAO.getJobStatuses(requestId);
            requestDescription.setJobStatuses(jobs);
            double percentageCompletion = ((double)jobs.size() / (double)requestDescription.getStartRequestStatus().getTotalTasks()) * 100;
            requestDescription.setPercentageCompletion(percentageCompletion);
        }
        return requestDescription;
    }
}
