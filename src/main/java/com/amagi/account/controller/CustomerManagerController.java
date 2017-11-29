package com.amagi.account.controller;

import com.amagi.account.pojo.customer.CustomerBasicInfo;
import com.amagi.account.pojo.response.Response;
import com.amagi.account.service.api.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerManagerController {

    @Autowired
    private CustomerManager customerManager;

    @RequestMapping(value = "/get-customers", method = RequestMethod.GET)
    public ResponseEntity<Response> getCustomers(@RequestHeader HttpHeaders httpHeaders, @RequestParam Map<String, String> parameters) {
        List<CustomerBasicInfo> customers = customerManager.getCustomerList();
        Response response = new Response.Builder<>().setDataAsList(customers).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "get-customer-info", method = RequestMethod.GET)
    public ResponseEntity<Response> getCustomerIngo(@RequestHeader HttpHeaders httpHeaders, @RequestParam Map<String, String> parameters) {
        String customerId = parameters.get("customer_id");
        Response response = new Response.Builder<>().setDataAsClassObject(customerManager.getCustomerDetailInfo(customerId)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
