package com.peng.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.peng.dto.LoginResult;
import com.peng.dto.Result;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.service.AdminLoginService;

@RestController
public class LoginController {

	@Resource
    private AdminLoginService loginService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody TAdminUser user) {
        System.out.println("login()");
        String userName = user.getUserName();
        String password = user.getPassword();
        
        LoginResult loginResult = loginService.login(userName,password);
        if(loginResult.isLogin()){
            return new Result(100001,true,"成功");
        } else {
            return new Result(100004,false,"登录失败：" + loginResult.getResult());
        }
    }

    @GetMapping(value = "/adminindex")
    public String index() {
        return "主页";
    }

    @GetMapping(value = "/adminlogout")
    public String logout() {
        return "退出";
    }

    
}
