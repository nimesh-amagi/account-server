package com.amagi.account.security.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * In case of Google authentication form is used for signing in, then this class will help in validation.
 * Flow:
 *  1. SSP UI will receive one-time code from Google API
 *  2. UI will call /auth/login-google API with this one-time code
 *  3. Then here, using google-client-api will authenticate this one-time code from Google API Server
 *  4. For valid code, we would receive User information in Payload, from that we will extract necessary fields
 *  5. Based on these fields, we will validate user in our DB
 *  6. For non-valid code, we would receive Null/Exception from google-client-api, we would invalidate this request
 */
public class GoogleAuthcodeValidator {

    static Logger logger = LoggerFactory.getLogger(GoogleAuthcodeValidator.class);

    private static final String CLIENT_SECRET = "/var/account-server/configs/client_secret.json";
    private static final String REDIRECT_URI = "postmessage";
    private static final String GOOGLE_SERVER_ENCODED_URL = "https://www.googleapis.com/oauth2/v4/token";

    public static GoogleUser validate(String authCode) {
        try {
            File file = new File(CLIENT_SECRET);
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(
                            JacksonFactory.getDefaultInstance(), new FileReader(file));
            GoogleTokenResponse tokenResponse =
                    new GoogleAuthorizationCodeTokenRequest(
                            new NetHttpTransport(),
                            JacksonFactory.getDefaultInstance(),
                            "https://www.googleapis.com/oauth2/v4/token",
                            clientSecrets.getDetails().getClientId(),
                            clientSecrets.getDetails().getClientSecret(),
                            authCode,
                            REDIRECT_URI).execute();
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            if (emailVerified) {
                String userId = payload.getSubject();
                String name = (String) payload.get("name");
                String locale = (String) payload.get("locale");
                return new GoogleUser(email, userId, name, locale, true);
            }
            logger.error("Google auth code validation failed for [%s], because email is not verified", email);
            return GoogleUser.getInValidUser();
        } catch (IOException e) {
            logger.error("Error in validating with google-client-api:");
            logger.error(e.getMessage());
            return GoogleUser.getInValidUser();
        }
    }
}
