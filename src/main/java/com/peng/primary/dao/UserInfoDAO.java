package com.peng.primary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peng.primary.entity.TUserInfo;


public interface UserInfoDAO extends JpaRepository<TUserInfo,Integer>{

	
}
