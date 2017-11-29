package com.amagi.account.controller;

import com.amagi.account.pojo.response.Response;
import com.amagi.account.security.google.GoogleAuthcodeValidator;
import com.amagi.account.security.google.GoogleUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class will serve as RESTController for all authentication and authorization related REST end points
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login-google")
    public ResponseEntity<Response> loginGoogle(@RequestHeader HttpHeaders headers, @RequestParam Map<String, String> params) {
        if (headers.getFirst("X-Requested-With") == null && headers.getFirst("X-Requested-With").isEmpty()) {
            Response response = new Response.Builder()
                                    .setStatus(401)
                                    .setErrors(Arrays.asList("Unauthorized request"))
                                    .setMessages(Arrays.asList("Not allowed"))
                                .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        GoogleUser googleUser = GoogleAuthcodeValidator.validate(extractAuthcode(params));
        if (googleUser.isValid()) {
            Response response = new Response.Builder()
                                    .setStatus(200)
                                    .setMessages(Arrays.asList("allowed"))
                                    .setDataAsClassObject(googleUser)
                                    .build();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        Response response = new Response.Builder()
                                .setStatus(401)
                                .setErrors(Arrays.asList("Unauthorized request"))
                                .setMessages(Arrays.asList("Not allowed"))
                                .setDataAsClassObject(googleUser)
                                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    private String extractAuthcode(Map<String, String> params) {
        return params.keySet().stream().collect(Collectors.toList()).get(0);
    }
}
