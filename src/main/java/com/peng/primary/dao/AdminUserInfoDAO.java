package com.peng.primary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peng.primary.entity.TAdminUser;


public interface AdminUserInfoDAO extends JpaRepository<TAdminUser,Long>{

	TAdminUser findByUserName(String userName);
	
}
