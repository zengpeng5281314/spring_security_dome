package com.peng.test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.peng.primary.entity.TAdminRolePermission;
import com.peng.primary.service.AdminRolePemissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZengpengApplicationTests {

	@Autowired
	private AdminRolePemissionService adminRolePemissionService;

	@Test
	public void contextLoads() {
		List<TAdminRolePermission> list = adminRolePemissionService.findByRoleId(1);

		for (TAdminRolePermission tAdminRolePermission : list) {
			System.out.println(
					"-----"+tAdminRolePermission.getId() + "    " + tAdminRolePermission.getAdminRole().getRole()
							+ "    " + tAdminRolePermission.getAdminPermission().getPermissionName());

		}
		
		TAdminRolePermission adminRolePermission = new TAdminRolePermission();
		adminRolePermission.setRoleId(20l);
		adminRolePermission.setPermissionId(20l);
		adminRolePemissionService.saveAndFlush(adminRolePermission);

	}

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[32];
		random.nextBytes(bytes);
		String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
		System.out.println("salt:" + salt + "    " + salt.length());
		System.out.println("bytes:" + Arrays.toString(bytes));

		System.out.println("8d78869f470951332959580424d4bf4f".length());
		Md5Hash md5Hash = new Md5Hash("123456", "admin" + "8d78869f470951332959580424d4bf4f", 3);
		System.out.println(md5Hash); // 5f4dcc3b5aa765d61d8327deb882cf99
		System.out.println(md5Hash.toString());
	}

}
