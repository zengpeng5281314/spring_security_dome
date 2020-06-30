package com.peng.primary.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.peng.primary.dao.AdminRoleInfoDAO;
import com.peng.primary.entity.TAdminRole;
import com.peng.primary.service.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private AdminRoleInfoDAO adminRoleInfoDAO;

	@Override
	public Page<TAdminRole> findAll(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
		return adminRoleInfoDAO.findAll(pageable);

	}

	@Override
	public TAdminRole findByRoleId(long roleId) {
		Optional<TAdminRole> optionalT = adminRoleInfoDAO.findById(roleId);
		return optionalT.isPresent() ? optionalT.get() : null;
	}

	@Override
	public TAdminRole saveAndFlush(TAdminRole adminRole) {
		return adminRoleInfoDAO.saveAndFlush(adminRole);
	}

}
