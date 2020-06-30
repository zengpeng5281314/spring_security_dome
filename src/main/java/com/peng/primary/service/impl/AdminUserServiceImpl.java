package com.peng.primary.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.peng.dto.AdminUserRequestDTO;
import com.peng.primary.dao.AdminUserInfoDAO;
import com.peng.primary.dao.AdminUserRoleInfoDAO;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.service.AdminUserService;
import com.peng.util.DtoCopyEntity;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserInfoDAO adminUserInfoDAO;
	
	@Autowired
	private AdminUserRoleInfoDAO adminUserRoleInfoDAO;
	
	

	@Override
	public TAdminUser findByUserName(String userName) {
		return adminUserInfoDAO.findByUserName(userName);
	}

	@Override
	public Page<TAdminUser> findAll(int pageNumber, int pageSize) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC, "createTime"); // 创建时间降序排序
			Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
			return adminUserInfoDAO.findAll(pageable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public TAdminUser findByUserId(long userId) {
		Optional<TAdminUser> adminUser = adminUserInfoDAO.findById(userId);
		return adminUser.get();
	}

	@Override
	public TAdminUser saveAndFlush(AdminUserRequestDTO adminUserDTO) {
		TAdminUser adminUser = new TAdminUser();
		if (adminUserDTO.getUserId()==null || adminUserDTO.getUserId() == 0) {
			// 新增
			DtoCopyEntity.populate(adminUserDTO, adminUser);
			adminUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
//			String salt = saltUtils.getRandom32Salt();
//			adminUser.setSalt(salt);
//			adminUser.setPassword(saltUtils.getPwdBysalt(adminUser.getPassword(), adminUser.getCredentialsSalt(), 3));
		} else {
			// 修改
			adminUser = findByUserId(adminUserDTO.getUserId());
			if(StringUtils.isNotBlank(adminUserDTO.getEmail())){
				adminUser.setEmail(adminUserDTO.getEmail());
			}
			if(StringUtils.isNotBlank(adminUserDTO.getPassword())){
				adminUser.setPassword(adminUserDTO.getPassword());
			}
			if(StringUtils.isNotBlank(adminUserDTO.getPhone())){
				adminUser.setPhone(adminUserDTO.getPhone());
			}
			if(StringUtils.isNotBlank(adminUserDTO.getUserName())){
				adminUser.setUserName(adminUserDTO.getUserName());
			}
		}
		return adminUserInfoDAO.saveAndFlush(adminUser);
	}

	@Override
	public void deleteAdminUsers(String userIds) {
		String[] listIds = userIds.split(",");
		for (String userId : listIds) {
			if(StringUtils.isNotBlank(userId)){
				//删除用户管理关系
				adminUserRoleInfoDAO.deleteByUserId(Long.valueOf(userId));
				//删除用户信息
				adminUserInfoDAO.deleteById(Long.valueOf(userId));
			}
		}
		
	}

}
