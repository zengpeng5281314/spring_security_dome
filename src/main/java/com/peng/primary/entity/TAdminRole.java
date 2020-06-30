package com.peng.primary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;;

@Entity
@Getter
@Setter
@Table(name="t_admin_role")
public class TAdminRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2218061482482886329L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Long roleId; // 编号

	@Column(nullable = false, unique = true)
	private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

	private String description; // 角色描述,UI界面显示使用

	private Boolean available = Boolean.TRUE; // 是否可用,如果不可用将不会添加给用户

	// 角色 -- 权限关系：多对多关系;
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "t_admin_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "permission_id") })
	@Transient
	private List<TAdminPermission> permissions;

	// 用户 - 角色关系定义;
//	@ManyToMany
//	@JoinTable(name = "t_admin_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "user_id") })
	
	@Transient
	private List<TAdminUser> users;// 一个角色对应多个用户
}
