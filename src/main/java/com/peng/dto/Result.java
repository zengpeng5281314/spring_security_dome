package com.peng.dto;

public class Result<T> {

    /**
     * 对外返回的对象
     */
    private T result;

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    private boolean success;

    public Result() {
        super();
    }

    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result( int code,  boolean success ,String msg) {
        super();
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public Result(T result, int code, String msg) {
        super();
        this.result = result;
        this.code = code;
        this.msg = msg;
    }

    public Result(T result, int code, String msg, boolean success) {
        super();
        this.result = result;
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "{\"code\": "+code+",\"msg\": \""+msg+"\",\"success\": "+success+",\"timestamp\": "+System.currentTimeMillis()+"}";
    }
}
