package com.peng.primary.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peng.primary.dao.AdminUserRoleInfoDAO;
import com.peng.primary.entity.TAdminRole;
import com.peng.primary.entity.TAdminUserRole;
import com.peng.primary.service.AdminRoleService;
import com.peng.primary.service.AdminUserRoleService;

@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

	@Autowired
	private AdminUserRoleInfoDAO adminUserRoleInfoDAO;
	@Autowired
	private AdminRoleService adminRoleService;
	

	@Override
	public TAdminUserRole saveAndFlush(TAdminUserRole adminUserRole) {
		return adminUserRoleInfoDAO.saveAndFlush(adminUserRole);
	}

	@Override
	public List<TAdminUserRole> findByUserId(long userId) {
		return adminUserRoleInfoDAO.getTAdminUserRoleListByUserId(userId);
	}

	@Override
	public void saveAndFlush(long userId, String roleIds) {
		String[] role= roleIds.split(",");
		adminUserRoleInfoDAO.deleteByUserId(userId);
		for (String roleid : role) {
			if(StringUtils.isBlank(roleid))
				continue;
			TAdminRole adminRole = adminRoleService.findByRoleId(Long.parseLong(roleid));
			if(adminRole!=null){
				TAdminUserRole adminUserRole = new TAdminUserRole();
				adminUserRole.setRoleId(adminRole.getRoleId());
				adminUserRole.setUserId(userId);
				saveAndFlush(adminUserRole);
			}
		}
		
	}

}
