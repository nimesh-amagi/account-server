package com.amagi.account.controller;

import com.amagi.account.pojo.request.JobStatus;
import com.amagi.account.pojo.request.RequestDescription;
import com.amagi.account.pojo.request.RequestStatus;
import com.amagi.account.pojo.response.Response;
import com.amagi.account.service.api.RequestManager;
import com.amagi.account.validator.JobStatusValidator;
import com.amagi.account.validator.RequestStatusValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@RestController
public class RequestController {

    private static Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    RequestStatusValidator requestStatusValidator;

    @Autowired
    JobStatusValidator jobStatusValidator;

    @Autowired
    private RequestManager requestManager;

    @InitBinder("requestStatus")
    void bindRequestStatusBinder(WebDataBinder binder) {
        binder.setValidator(requestStatusValidator);
    }

    @InitBinder("jobStatus")
    void bindJobStatusValidator(WebDataBinder binder) {
        binder.setValidator(jobStatusValidator);
    }

    @RequestMapping(value = "/set-request-status", method = RequestMethod.POST)
    public ResponseEntity<Response> setRequestStatus(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody String parameters) {
        try {
            logger.info("Request: " + parameters);
            RequestStatus requestStatus = new ObjectMapper().readValue(parameters, RequestStatus.class);
            Boolean status = requestManager.saveRequest(requestStatus);
            Map<String, String> result = Collections.singletonMap("status", status ? "request_status updated successfully" : "request_status already exists in DB");
            Response response = new Response.Builder<String>().setDataAsMap(result).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response.Builder<>().setStatus(-1).setOneMessage(e.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/set-job-status", method = RequestMethod.POST)
    public ResponseEntity<Response> setJobStatus(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody String parameters) {
        try {
            logger.info("Job: " + parameters);
            JobStatus jobStatus = new ObjectMapper().readValue(parameters, JobStatus.class);
            Boolean status = requestManager.saveJobStatus(jobStatus);
            Response response = new Response.Builder<String>().setDataAsMap(Collections.singletonMap("status", "job status updated successfully")).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response.Builder<>().setStatus(-1).setOneMessage(e.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "get-job-status", method = RequestMethod.GET)
    public ResponseEntity<Response> getJobStatusForRequest(@RequestHeader HttpHeaders httpHeaders, @RequestParam Map<String, String> parameters) {
        String requestId = parameters.get("request_id");
        if (requestId == null || requestId.isEmpty()) {
            return new ResponseEntity<>(new Response.Builder<String>().setStatus(-1)
                                    .setDataAsMap(Collections.singletonMap("reason", "request_id is absent")).build(), HttpStatus.BAD_REQUEST);
        }
        RequestDescription requestDescription = requestManager.getRequestDescription(requestId);
        Response response = new Response.Builder<>().setDataAsClassObject(requestDescription).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
