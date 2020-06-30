package com.peng.primary.service;

import org.springframework.data.domain.Page;

import com.peng.dto.AdminUserRequestDTO;
import com.peng.primary.entity.TAdminUser;

public interface AdminUserService {
	
	TAdminUser findByUserName(String userName);
	
	TAdminUser saveAndFlush(AdminUserRequestDTO adminUser);
	
	TAdminUser findByUserId(long userId);
	
	Page<TAdminUser> findAll(int pageNumber,int pageSize);
	
	void deleteAdminUsers(String userIds);
}
