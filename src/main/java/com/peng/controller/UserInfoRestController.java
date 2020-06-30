package com.peng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.peng.config.RedisUtil;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.entity.TUserInfo;
import com.peng.primary.service.AdminUserService;
import com.peng.primary.service.UserInfoService;
import com.peng.secondary.service.UserInfoService2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


@Api(tags = "用户信息")
@RestController
public class UserInfoRestController {

	@Value("${person.age}")
	private String personAge;

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserInfoService2 userInfoService2;
	@Autowired
	private AdminUserService amdinUserService;
	
	@Autowired
	private RedisUtil redisUtil;


	@PostMapping("/dologin")
	@ResponseBody
	public String dologin(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		JSONObject json = new JSONObject();
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			if (username.equals("admin") && password.equals("123456")) {
				json.put("success", true);
				json.put("msg", "成功");
			} else {
				json.put("success", false);
				json.put("msg", "用户名密码不正确！");
			}
		} else {
			json.put("success", false);
			json.put("msg", "用户名密码不能为空！");
		}
		return json.toString();
	}


	@ApiOperation(value = "获取用户列表", notes = "获取所有的用户信息",httpMethod="POST")
	@ApiImplicitParam(name = "pageNumber", value = "保险公司编码", required = true, dataType = "Integer", paramType = "query",defaultValue="1")
	@PostMapping("/getuserlist")
	@ResponseBody
	public String getUserList(@RequestParam(defaultValue = "1") Integer pageNumber, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<TUserInfo> list = userInfoService.findAll();
		List<com.peng.secondary.entity.TUserInfo> list2 = userInfoService2.findAll();

		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("msg", "成功");
		json.put("list", list);
		json.put("list2", list2);
		return json.toString();
	}
	
	

}
