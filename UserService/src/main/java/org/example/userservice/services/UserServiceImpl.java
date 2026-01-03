package org.example.userservice.services;

import org.example.userservice.exceptions.ExpiredTokenException;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signup(String name, String email, String password) throws Exception {
        Optional<User> optionalUser = this.userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            throw new Exception("User already present");
        }
        String encodedPassword=this.bCryptPasswordEncoder.encode(password);
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encodedPassword);
        return this.userRepository.save(user);

    }

    @Override
    public Token login(String email, String password) throws Exception {
        return null;
    }

    @Override
    public Token validateToken(String tokenValue) throws InvalidTokenException, ExpiredTokenException {
        return null;
    }

    @Override
    public void logout(String tokenValue) throws Exception {
        Optional<Token> tokenByValue=this.tokenRepository.findTokenByValue(tokenValue);
        Token token=tokenByValue.orElseThrow(()->new InvalidTokenException("Please use a valid token"));
        if(token.isActive()){
            token.setActive(false);
            this.tokenRepository.save(token);
        }
    }
}
