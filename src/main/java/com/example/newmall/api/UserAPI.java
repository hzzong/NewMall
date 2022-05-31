package com.example.newmall.api;

import com.example.newmall.api.param.UserLoginParam;
import com.example.newmall.api.param.UserRegisterParam;
import com.example.newmall.api.vo.UserVO;
import com.example.newmall.common.Constants;
import com.example.newmall.common.ServiceEnum;
import com.example.newmall.config.annotation.TokenToUser;
import com.example.newmall.domain.User;
import com.example.newmall.service.UserService;
import com.example.newmall.util.PhoneUtil;
import com.example.newmall.util.Result;
import com.example.newmall.util.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserAPI {

    private PhoneUtil phoneUtil;

    private ResultGenerator resultGenerator;

    private UserService userService;

    @Autowired
    public UserAPI(PhoneUtil phoneUtil, ResultGenerator resultGenerator, UserService userService) {
        this.phoneUtil = phoneUtil;
        this.resultGenerator = resultGenerator;
        this.userService = userService;
    }



    @PostMapping("/user/register")
    public Result register(@RequestBody @Valid UserRegisterParam param){
        if (phoneUtil.isNotPhoneNumber(param.getLoginName())){
            return resultGenerator.genFailResult(ServiceEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String registerResult = userService.register(param.getLoginName(), param.getPassword());
        log.info("register api,loginName={},loginResult={}", param.getLoginName(), registerResult);

        //注册成功
        if (ServiceEnum.SUCCESS.getResult().equals(registerResult)) {
            return resultGenerator.genSuccessResult();
        }
        //注册失败
        return resultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/user/login")
    public  Result login(@RequestBody @Valid UserLoginParam param){
        if (phoneUtil.isNotPhoneNumber(param.getLoginName())){
            return resultGenerator.genFailResult(ServiceEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = userService.login(param.getLoginName(), param.getPassword());
        log.info("login api,loginName={},loginResult={}", param.getLoginName(), loginResult);

        //登录成功
        if (!StringUtils.isBlank(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = resultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return resultGenerator.genFailResult(loginResult);
    }

    @GetMapping("/user/info")
    public Result<UserVO> getUserDetail(@TokenToUser User user) {
        //已登录则直接返回
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return resultGenerator.genSuccessResult(userVO);
    }
}
