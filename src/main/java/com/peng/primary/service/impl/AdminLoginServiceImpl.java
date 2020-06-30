package com.peng.primary.service.impl;


import org.springframework.stereotype.Service;

import com.peng.dto.LoginResult;
import com.peng.primary.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Override
	public  LoginResult login(String userName, String password){
		LoginResult loginResult = new LoginResult();
        if (userName == null || userName.isEmpty()) {
            loginResult.setLogin(false);
            loginResult.setResult("用户名为空");
            return loginResult;
        }
        String msg = "";
       
        loginResult.setLogin(false);
        loginResult.setResult(msg);

        return loginResult;
	}

	@Override
    public void logout() {
    }
}
