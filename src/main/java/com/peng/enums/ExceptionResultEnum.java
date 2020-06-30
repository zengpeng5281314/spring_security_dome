package com.peng.enums;

public enum ExceptionResultEnum {

	// 枚举维护,将异常统一在一个地方进行维护,
	// 这样的好处就是在以后你要改语句的情况下,通过枚举
	// 不需要动其他地方的业务逻辑代码,只需要改动这里的语句就可以了
	UNKONW_ERROR(-1, "未知错误"), SUCCESS(0, "成功"), PRIMARY_SCHOOL(100, "我猜测,你可能还在上小学"), MIDDLE_SCHOOL(101, "你可能在上初中"),;
	private Integer code;
	private String message;

	ExceptionResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	// 枚举里面只要给get方法就可以了,因为枚举的使用都是直接用构造方法来创建,不会再从新set
	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
