package com.amagi.account.validator;

import com.amagi.account.pojo.request.JobStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JobStatusValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return JobStatus.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JobStatus jobStatus = (JobStatus) target;
        if (jobStatus.getRequestId() == null || jobStatus.getRequestId().isEmpty()) {
            errors.reject("request_id", "request_id is either NULL or EMPTY, please give valid input");
        }
    }
}
