package com.amagi.account.controller;

import com.amagi.account.dao.api.SetupDAO;
import com.amagi.account.pojo.response.Response;
import com.amagi.account.pojo.response.SetupResult;
import com.amagi.account.pojo.response.SetupResult.SetupStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * This controller will create complete table setup on database of deployed system.
 * It will have only one API url and different authentication should be implemented for this API call.
 * Database connection details should be provided in application.properties file.
 * Excepted: at least database name (same name as given in properties file)
 * Required tables will be created and if required default values will populated
 */
@RestController
public class SetupController {

    @Autowired
    SetupDAO setupDAO;

    @RequestMapping("/initiate-setup")
    public ResponseEntity<Response> initiateSetup() {
        SetupResult result = setupDAO.setupERPDB();
        Response response = null;
        if (result.getStatus() == SetupStatus.SUCCESS) {
            response = new Response.Builder<>()
                    .setDataAsMap(Collections.singletonMap("status", result.getStatus()))
                    .build();
        } else {
            response = new Response.Builder()
                    .setStatus(-1)
                    .setOneMessage(result.getMessage())
                    .setOneError(result.getException().getMessage())
                    .build();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
