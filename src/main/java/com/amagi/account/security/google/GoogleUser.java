package com.amagi.account.security.google;

import com.amagi.account.security.AuthUser;

/**
 * This class is simple POJO, to hold payload information received from Google API Server
 */
public class GoogleUser implements AuthUser {
    private final String email;
    private final String googleUserId;
    private final String name;
    private final String locale;
    private final boolean isValid;

    public GoogleUser(String email, String googleUserId, String name, String locale, boolean isValid) {
        this.email = email;
        this.googleUserId = googleUserId;
        this.name = name;
        this.locale = locale;
        this.isValid = isValid;
    }

    public String getEmail() {
        return email;
    }

    public String getGoogleUserId() {
        return googleUserId;
    }

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }

    public boolean isValid() {
        return isValid;
    }

    protected static GoogleUser getInValidUser() {
        return new GoogleUser(null, null, null, null, false);
    }
}
