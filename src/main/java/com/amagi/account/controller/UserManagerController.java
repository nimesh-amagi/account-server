package com.amagi.account.controller;

import com.amagi.account.base.BaseDatabaseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as REST Controller for User Management APIs
 */
@RestController
public class UserManagerController {

    @Autowired
    BaseDatabaseSetup baseDatabaseSetup;

    @RequestMapping("/get-users")
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        users.add("1");users.add("1");users.add("1");users.add("1");users.add("1");
        baseDatabaseSetup.init();
        return users;
    }
}
