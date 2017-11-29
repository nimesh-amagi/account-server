package com.amagi.account.validator;

import com.amagi.account.pojo.request.RequestStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RequestStatusValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RequestStatus.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestStatus requestStatus = (RequestStatus) target;
        if (requestStatus.getRequestId() == null || requestStatus.getRequestId().isEmpty()) {
            errors.reject("request_id", "request_id is either NULL or EMPTY, please give valid input");
        }
        if (requestStatus.getCustomerId() == null || requestStatus.getCustomerId().isEmpty()) {
            errors.reject("customer_id", "customer_id is either NULL or EMPTY, please give valid input");
        }
        if (requestStatus.getTotalTasks() == null) {
            errors.reject("total_task", "total_tasks related field is absent from input, give a valid integer value");
        }
    }
}
