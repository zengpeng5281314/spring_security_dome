package com.peng.util;

import java.security.SecureRandom;

import org.apache.shiro.crypto.hash.Md5Hash;

public class saltUtils {
	
	/**
	 * 生成一个随机32位的盐值
	 * @return
	 */
	public static String getRandom32Salt(){
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[32];
		random.nextBytes(bytes);
		String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
		return salt;
	}
	
	/**
	 * md5加密
	 * @param pwd
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static String getPwdBysalt(String pwd ,String salt,int hashIterations){
		Md5Hash md5Hash = new Md5Hash(pwd,salt,3);
		return md5Hash.toString();
	}

}
