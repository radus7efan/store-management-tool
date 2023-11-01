package com.demo.smt.service;

import com.demo.smt.exception.StoreManagementToolException;
import com.demo.smt.model.rest.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public Token authenticate(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            var auth = new Token();
            auth.setToken(jwtService.generateToken(username));
            return auth;
        }

        throw new StoreManagementToolException(HttpStatus.NOT_FOUND,
                "Could not find the User with username: %s!", username);
    }


}
