package com.peng.primary.service;

import com.peng.dto.LoginResult;

public interface AdminLoginService {

	 LoginResult login(String userName, String password);

	 void logout();
	    
}
