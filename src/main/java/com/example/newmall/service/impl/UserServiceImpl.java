package com.example.newmall.service.impl;

import com.example.newmall.common.ServiceEnum;
import com.example.newmall.domain.User;
import com.example.newmall.domain.repo.UserRepo;
import com.example.newmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String register(String loginName, String password){
        if (userRepo.findUserByLoginName(loginName) != null) {
            return ServiceEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign("Welcome!");
        registerUser.setPasswordMd5(password);
        if (userRepo.save(registerUser) != null) {
            return ServiceEnum.SUCCESS.getResult();
        }
        return ServiceEnum.DB_ERROR.getResult();
    }
}
