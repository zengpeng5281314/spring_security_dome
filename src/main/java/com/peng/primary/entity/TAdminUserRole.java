package com.peng.primary.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;;

@Entity
@Getter
@Setter
@Table(name="t_admin_user_role")
public class TAdminUserRole implements Serializable {

	private static final long serialVersionUID = 3983583750937065145L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id; // 编号
	
	@Column(name="role_id")
	private Long roleId; // 编号
	
	@Column(name="user_id")
	private Long userId; // 编号
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id",referencedColumnName="role_id",nullable=false, insertable = false, updatable = false)
	private TAdminRole adminRole;
	
	
}
