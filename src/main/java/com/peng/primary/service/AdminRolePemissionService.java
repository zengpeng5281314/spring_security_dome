package com.peng.primary.service;

import java.util.List;

import com.peng.primary.entity.TAdminRolePermission;

public interface AdminRolePemissionService {
	
	TAdminRolePermission saveAndFlush(TAdminRolePermission adminRolePermission);
	
	List<TAdminRolePermission> findByRoleId(long roleId);

}
