package com.peng.primary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peng.primary.entity.TAdminRolePermission;

public interface AdminRolePemissionInfoDAO extends JpaRepository<TAdminRolePermission, Long>,JpaSpecificationExecutor<TAdminRolePermission> {

	@Query(value = "SELECT b FROM TAdminRolePermission b where b.roleId = :roleId order by b.id desc")
	public List<TAdminRolePermission> getTAdminRolePermissionListByRoleId(@Param("roleId")long roleId);

}
