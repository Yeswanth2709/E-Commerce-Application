package org.example.userservice.services;

import org.example.userservice.exceptions.*;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;

public interface UserService {
    User signup(String name, String email, String password) throws Exception;

    Token login(String email, String password) throws Exception;

    Token validateToken(String tokenValue) throws InvalidTokenException, ExpiredTokenException;

    void logout(String token) throws Exception;
}
