package com.peng.primary.service;

import java.util.List;

import com.peng.primary.entity.TAdminUserRole;

public interface AdminUserRoleService {
	
	TAdminUserRole saveAndFlush(TAdminUserRole adminUserRole);
	
	List<TAdminUserRole> findByUserId(long userId);
	
	void saveAndFlush(long userId,String roleIds);

}
