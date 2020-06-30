package com.peng.primary.service;

import org.springframework.data.domain.Page;

import com.peng.primary.entity.TAdminRole;

public interface AdminRoleService {
	
	TAdminRole saveAndFlush(TAdminRole adminRole);
	
	TAdminRole findByRoleId(long roleId);
	
	Page<TAdminRole> findAll(int pageNumber,int pageSize);
	
	
	
}
