package com.peng.primary.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;;

@Entity
@Getter
@Setter
@Table(name="t_admin_role_permission")
public class TAdminRolePermission implements Serializable {

	private static final long serialVersionUID = 3895132655660193841L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id; // 编号
	
	@Column(name="role_id")
	private Long roleId; // 编号
	
	@Column(name="permission_id")
	private Long permissionId; // 编号

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id",referencedColumnName="role_id",nullable=false, insertable = false, updatable = false)
	private TAdminRole adminRole;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="permission_id",referencedColumnName="permission_id",nullable=false, insertable = false, updatable = false)
	private TAdminPermission adminPermission;
	
}
