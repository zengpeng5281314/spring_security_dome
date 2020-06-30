package com.peng.primary.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.peng.primary.entity.TAdminUserRole;

public interface AdminUserRoleInfoDAO extends JpaRepository<TAdminUserRole, Integer>,JpaSpecificationExecutor<TAdminUserRole> {

	@Query(value = "SELECT b FROM TAdminUserRole b where b.userId = :userId order by b.id desc")
	public List<TAdminUserRole> getTAdminUserRoleListByUserId(@Param("userId")long userId);
	
	@Query(value = "SELECT b FROM TAdminUserRole b where b.userId = :userId and b.roleId = :roleId")
	public TAdminUserRole getTAdminUserRoleByUseridAndRoleId(@Param("userId")long userId,@Param("roleId")long roleId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TAdminUserRole b where b.userId = :userId")
	public void deleteByUserId(@Param("userId")long userId);

}
