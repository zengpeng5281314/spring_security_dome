package com.peng.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peng.dto.AdminUserRequestDTO;
import com.peng.dto.AdminUserResponseDTO;
import com.peng.primary.entity.TAdminRole;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.entity.TAdminUserRole;
import com.peng.primary.service.AdminRoleService;
import com.peng.primary.service.AdminUserRoleService;
import com.peng.primary.service.AdminUserService;
import com.peng.util.DtoCopyEntity;
import com.peng.util.JsonDateValueProcessor;

import io.swagger.annotations.Api;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Api(tags = "用户页面")
@Controller
public class UserInfoController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminUserRoleService adminUserRoleService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/adminUserList", method = { RequestMethod.GET })
	@RequiresPermissions("user:view") // 权限管理;
	public String adminUserList(@RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		AdminUserRequestDTO adminUserRequestDTO = new AdminUserRequestDTO();
		adminUserRequestDTO.setPageNumber(pageNumber);
		adminUserRequestDTO.setPageSize(pageSize);
		Page<TAdminUser> page = adminUserService.findAll(adminUserRequestDTO.getPageNumber(),
				adminUserRequestDTO.getPageSize());
		List<TAdminUser> list = page.getContent();
		List<AdminUserResponseDTO> listresponse = new ArrayList<AdminUserResponseDTO>();
		String role = "";
		for (TAdminUser tAdminUser : list) {
			AdminUserResponseDTO adminUserResponseDTO = new AdminUserResponseDTO();
			DtoCopyEntity.populate(tAdminUser, adminUserResponseDTO);
			List<TAdminUserRole> userRoleList = adminUserRoleService.findByUserId(tAdminUser.getUserId());
			for (TAdminUserRole tAdminUserRole : userRoleList) {
				role += tAdminUserRole.getAdminRole().getRole() + "/";
			}
			adminUserResponseDTO.setRoles(role);
			listresponse.add(adminUserResponseDTO);
			role = "";
		}
		model.put("list", listresponse);
		model.put("total", page.getTotalElements());
		model.put("totalPages", page.getTotalPages());
		return "/user/admin-list";
	}

	@RequestMapping(value = "/adminUserAdd", method = { RequestMethod.GET })
	public String adminUserAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Page<TAdminRole> page = adminRoleService.findAll(1, 1000);
		model.put("RoleList", page.getContent());
		return "/user/admin-add";
	}

	@RequestMapping(value = "/adminUserUpdate", method = { RequestMethod.GET })
	public String adminUserAdd(@RequestParam(value = "userId", defaultValue = "0") Integer userId,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (userId != 0) {
			TAdminUser adminUser = adminUserService.findByUserId(userId);
			model.put("adminUser", adminUser);
			String roles = "[";

			List<TAdminUserRole> listUserRole = adminUserRoleService.findByUserId(userId);

			for (TAdminUserRole adminUserRole : listUserRole) {
				roles += adminUserRole.getRoleId() + ",";
			}
			roles += "0]";
			try {
				model.put("selectad", roles.toString());
				Page<TAdminRole> page = adminRoleService.findAll(1, 1000);
				model.put("roleList", page.getContent());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "/user/admin-edit";
	}

	@RequestMapping(value = "/403", method = { RequestMethod.GET })
	public String unauthorizedRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "403";
	}

}
