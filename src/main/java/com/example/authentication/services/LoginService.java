package com.example.authentication.services;

import com.example.authentication.models.User;
import com.example.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean AddUser(User newUser) {
        if (newUser == null) {
        	return false;
        }
        userRepository.save(newUser);
        return true;
    }

    public User GetUserByName(String name) {
    	return userRepository.findByName(name);
    }
    
    public User GetUserByEmail(String email) {
    	return userRepository.findByEmail(email);
    }
    
    public boolean AuthenticateByEmail(String email, String password) {
    	return userRepository.existsByEmailAndPassword(email, password);
    }

    public boolean AuthenticateUser(User user, String enteredPassword)
    {
        return (user.getPassword().equals(enteredPassword));
    }

}
