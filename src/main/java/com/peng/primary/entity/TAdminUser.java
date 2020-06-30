package com.peng.primary.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_admin_user")
public class TAdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632926211416215419L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@Column(nullable = false, unique = true, name = "user_name")
	private String userName; // 登录用户名

	@Column(nullable = false)
	private String phone;// 手机号

	@Column(nullable = false)
	private String password;

	private String salt;// 加密密码的盐

	@Column(name="state")
	private byte state=1;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
						// 1:正常状态,2：用户被锁定.

//	@ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据;
//	@JoinTable(name = "t_admin_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "role_id") })
	@Transient
	private List<TAdminRole> roleList;// 一个用户具有多个角色

	@Column(name = "create_time")
	private Timestamp createTime;// 创建时间

	@Column(name = "expired_date")
	private Timestamp expiredDate;// 过期日期

	private String email;

	/** 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解 */
	public String getCredentialsSalt() {
		return this.userName + this.salt;
	}
}
