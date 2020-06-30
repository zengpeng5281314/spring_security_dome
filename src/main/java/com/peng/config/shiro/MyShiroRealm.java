 	package com.peng.config.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.peng.primary.entity.TAdminPermission;
import com.peng.primary.entity.TAdminRole;
import com.peng.primary.entity.TAdminRolePermission;
import com.peng.primary.entity.TAdminUser;
import com.peng.primary.entity.TAdminUserRole;
import com.peng.primary.service.AdminRolePemissionService;
import com.peng.primary.service.AdminUserRoleService;
import com.peng.primary.service.AdminUserService;

public class MyShiroRealm extends AuthorizingRealm{
	
	 	@Autowired
	    private AdminUserService userService;
	 	@Autowired
	    private AdminUserRoleService adminUserRoleService;
	 	@Autowired
	 	private AdminRolePemissionService adminRolePemissionService;

	    /**
	     * 身份认证:验证用户输入的账号和密码是否正确。
	     * */
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	        //获取用户输入的账号
	        String userName = (String) token.getPrincipal();
	        //通过username从数据库中查找 User对象.
	        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	        TAdminUser user = userService.findByUserName(userName);
	        if (user == null) {
	            return null;
	        }
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	                user,//这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
	                user.getPassword(),//密码
	                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
	                getName()//realm name
	        );
	        return authenticationInfo;
	    }

	    /**
	     * 权限信息
	     * */
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        //如果身份认证的时候没有传入User对象，这里只能取到userName
	        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
	        TAdminUser user  = (TAdminUser)principals.getPrimaryPrincipal();
	        
	        List<TAdminUserRole> listUserRole = adminUserRoleService.findByUserId(user.getUserId());

	        for (TAdminUserRole tAdminUserRole : listUserRole) {
	        	 authorizationInfo.addRole(tAdminUserRole.getAdminRole().getRole());
	        	 List<TAdminRolePermission> listRolePermission= adminRolePemissionService.findByRoleId(tAdminUserRole.getRoleId());
	        	 for (TAdminRolePermission tAdminRolePermission : listRolePermission) {
	        		 authorizationInfo.addStringPermission(tAdminRolePermission.getAdminPermission().getPermission());
				}
	        	
			}
	        
	        
//	        for(TAdminRole role : user.getRoleList()){
//	            //添加角色
//	            authorizationInfo.addRole(role.getRole());
//	            for(TAdminPermission p:role.getPermissions()){
//	                //添加权限
//	            	authorizationInfo.addStringPermission(p.getPermission());
//	            }
//	        }
	        return authorizationInfo;
	    }
}
