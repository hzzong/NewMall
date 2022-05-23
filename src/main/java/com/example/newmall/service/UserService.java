package com.example.newmall.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String register(String login, String password);
}
