package com.peng.primary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peng.primary.dao.UserInfoDAO;
import com.peng.primary.entity.TUserInfo;

@Service
public class UserInfoService {

	@Autowired 
	UserInfoDAO userInfoDAO;
	
	public List<TUserInfo> findAll(){
		return userInfoDAO.findAll();
	}
	
}
