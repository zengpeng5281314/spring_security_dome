package com.peng.primary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="t_admin_permission")
public class TAdminPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7907059500823767523L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="permission_id")
	private Long permissionId;// 主键.

	@Column(nullable = false,name="permission_name")
	private String permissionName;// 名称.

	@Column(columnDefinition = "enum('menu','button')")
	private String resourceType;// 资源类型，[menu|button]

	private String url;// 资源路径.

	private String permission; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

	@Column(nullable = false,name="parent_id")
	private Long parentId; // 父编号

	@Column(nullable = false,name="parent_ids")
	private String parentIds; // 父编号列表

	private Boolean available = Boolean.TRUE;

//	// 角色 -- 权限关系：多对多关系;
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "t_admin_role_permission", joinColumns = { @JoinColumn(name = "permission_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "role_id") })
	@Transient
	private List<TAdminRole> roles;
}
