package com.peng.primary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peng.primary.dao.AdminRolePemissionInfoDAO;
import com.peng.primary.dao.AdminUserRoleInfoDAO;
import com.peng.primary.entity.TAdminRolePermission;
import com.peng.primary.entity.TAdminUserRole;
import com.peng.primary.service.AdminRolePemissionService;
import com.peng.primary.service.AdminUserRoleService;

@Service
public class AdminRolePemissionServiceImpl implements AdminRolePemissionService {

	@Autowired
	private AdminRolePemissionInfoDAO adminRolePemissionInfoDAO;


	@Override
	public TAdminRolePermission saveAndFlush(TAdminRolePermission adminRolePermission) {
		return adminRolePemissionInfoDAO.saveAndFlush(adminRolePermission);
	}


	@Override
	public List<TAdminRolePermission> findByRoleId(long roleId) {
		return adminRolePemissionInfoDAO.getTAdminRolePermissionListByRoleId(roleId);
	}

}
