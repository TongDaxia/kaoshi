package com.kaoshi.tyg.common;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {
    private String code;
    private String desc;
    private T biz;

    public CommonResponse() {
    }

    public CommonResponse(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CommonResponse(ReturnCode code, T biz) {
        this.code = code.getCode();
        this.desc = code.getDesc();
        this.biz = biz;

    }

    public CommonResponse(String code, String desc, T biz) {
        this.code = code;
        this.desc = desc;
        this.biz = biz;
    }

    public CommonResponse(CommonResponse response, T biz) {
        this.biz = biz;
        this.code = response.code;
        this.desc = response.desc;
    }

    public CommonResponse(ReturnCode success) {
        this.code = success.getCode();
        this.desc = success.getDesc();
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

    public T getBiz() {
        return biz;
    }

    public void setBiz(T biz) {
        this.biz = biz;
    }
}
