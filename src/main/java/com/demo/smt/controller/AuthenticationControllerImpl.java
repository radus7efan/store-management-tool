package com.demo.smt.controller;

import com.demo.smt.controller.rest.api.TokenApi;
import com.demo.smt.model.rest.Token;
import com.demo.smt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store-management")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements TokenApi {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<Token> obtainToken(String username, String password) {
        return new ResponseEntity<>(
                authenticationService.authenticate(username, password),
                HttpStatus.OK
        );
    }
}
