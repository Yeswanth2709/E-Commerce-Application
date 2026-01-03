package org.example.userservice.controllers;

import org.example.userservice.dtos.LoginRequestDto;
import org.example.userservice.dtos.LogoutRequestDto;
import org.example.userservice.dtos.SignUpRequestDto;
import org.example.userservice.dtos.ValidateTokenRequestDto;
import org.example.userservice.exceptions.*;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto requestDto){
        try{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(201));
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto requestDto){
        try{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(200));
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        try{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(200));
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Token> validateToken(@RequestBody ValidateTokenRequestDto requestDto){
        try{
            // TODO basic validations
            Token token = this.userService.validateToken(requestDto.getToken());
            return new ResponseEntity<>(token, HttpStatusCode.valueOf(200));
        } catch (ExpiredTokenException ete){
            return new ResponseEntity<>(HttpStatusCode.valueOf(401));
        } catch (InvalidTokenException ite){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }
}
