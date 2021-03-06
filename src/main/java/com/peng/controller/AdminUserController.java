package com.peng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peng.dto.AdminUserRequestDTO;
import com.peng.dto.Result;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.service.AdminRoleService;
import com.peng.primary.service.AdminUserRoleService;
import com.peng.primary.service.AdminUserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/adminuser")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminUserRoleService adminUserRoleService;

	// 用户查询
	@GetMapping("/userList")
	public Result userInfo(@RequestBody AdminUserRequestDTO adminUserRequestDTO) {
		Page<TAdminUser> page = adminUserService.findAll(adminUserRequestDTO.getPageNumber(),
				adminUserRequestDTO.getPageSize());
		page.getContent();
		page.getTotalPages();
		return new Result<>(page, 100001, "成功", true);
	}

	// 用户添加
	@GetMapping("/userAdd")
	public String userInfoAdd() {
		return "userAdd";
	}

	@ApiOperation(value = "新增/修改 用户", notes = "新增/修改 用户信息", httpMethod = "POST")
	@ApiImplicitParam(name = "pageNumber", value = "保险公司编码", required = true, dataType = "Integer", paramType = "query", defaultValue = "1")
	@PostMapping("/AddAdminUserInfo")
	public Result AddAdminUserInfo(@RequestBody AdminUserRequestDTO dminUserRequestDTO) {
		try {
			System.out.println("AddAdminUserInfo()");
			TAdminUser adminUser = adminUserService.saveAndFlush(dminUserRequestDTO);
			String roleIds = dminUserRequestDTO.getRoleList();
			adminUserRoleService.saveAndFlush(adminUser.getUserId(), roleIds);
			// String[] role= roleIds.split(",");
			// for (String roleid : role) {
			// TAdminRole adminRole =
			// adminRoleService.findByRoleId(Long.parseLong(roleid));
			// if(adminRole!=null){
			// TAdminUserRole adminUserRole = new TAdminUserRole();
			// adminUserRole.setRoleId(adminRole.getRoleId());
			// adminUserRole.setUserId(adminUser.getUserId());
			// adminUserRoleService.saveAndFlush(adminUserRole);
			// }
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new Result(100001, true, "成功");
	}

	// 用户删除
	@ApiOperation(value = "删除/批量 用户", notes = "删除用户信息", httpMethod = "POST")
	@ApiImplicitParam(name = "pageNumber", value = "保险公司编码", required = true, dataType = "Integer", paramType = "query", defaultValue = "1")
	@PostMapping("/delAdminUserInfo")
	public Result userDel(@RequestParam(defaultValue="0",name="userIds") String userIds) {
//	public Result userDel(@RequestBody DeleteAdminUserRequestDTO deleteAdminUserRequestDTO) {
//		String userIds = deleteAdminUserRequestDTO.getUserIds();
		adminUserService.deleteAdminUsers(userIds);
		return new Result(100001, true, "成功");
	}

}
