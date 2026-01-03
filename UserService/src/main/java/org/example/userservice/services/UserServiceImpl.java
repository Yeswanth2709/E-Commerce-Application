package org.example.userservice.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.userservice.exceptions.ExpiredTokenException;
import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordMissMatchException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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
        Optional<User> optionalUser=userRepository.findUserByEmail(email);
        User user=optionalUser.orElseThrow(()->new Exception("User not found"));

        boolean matches = this.bCryptPasswordEncoder.matches(password,user.getPassword());
        if(matches){
            String value = RandomStringUtils.randomAlphanumeric(128);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 30);
            Date thirtyDaysLater =c.getTime();

            Token token = new Token();
            token.setUser(user);
            token.setValue(value);
            token.setExpiresAt(thirtyDaysLater);
            token.setActive(true);
            return this.tokenRepository.save(token);
        }else{
            throw new PasswordMissMatchException("Password is incorrect");
        }
    }

    @Override
    public Token validateToken(String tokenValue) throws InvalidTokenException, ExpiredTokenException {
        Optional<Token> tokenByValue = this.tokenRepository.findTokenByValue(tokenValue);

        Token token = tokenByValue.orElseThrow(() -> new InvalidTokenException("Please use a valid token"));

        Date expiresAt = token.getExpiresAt();
        Date now = new Date();
        // If now is greater than expires at
        if(now.after(expiresAt) || !token.isActive()){
            throw new ExpiredTokenException("The token has expired");
        }
        return token;
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
