package com.example.newmall.api;

import com.example.newmall.api.param.UserRegisterParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserAPI {

    @PostMapping("/user/register")
    public String register(@RequestBody @Valid UserRegisterParam param){

        return "Hello";
    }
}
