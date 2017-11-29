package com.amagi.account.security;

/**
 * Created by nimesh on 12/9/17.
 */
public interface AuthUser {

    String getEmail();

    default String getDbId() {
        return "";
    }
}
