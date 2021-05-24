package com.kaoshi.tyg.common;

public class ReturnCode {

    private String code;
    private String desc;

    public ReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static final ReturnCode SUCCESS = new ReturnCode("0000", "sucess");
    public static final ReturnCode ERROR = new ReturnCode("9999", "error");
    public static final ReturnCode PARAM_ERROR = new ReturnCode("0001", "请求参数错误");
    public static final ReturnCode _ERROR = new ReturnCode("0001", "请求参数错误");
}
