package com.peng.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdminUserResponseDTO {

	private int pageNumber=1;
	private int pageSize=20;
	
	private Long userId;
	private String userName; // 登录用户名
	private String phone;// 手机号
	private String password;
	private String email;
	private byte state=1;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
						// 1:正常状态,2：用户被锁定.
	private String roles;// 一个用户具有多个角色
	private Timestamp createTime;// 创建时间
	private Timestamp expiredDate;// 过期日期

}
