package com.oranfish.sushiweb.vo;

import java.io.Serializable;

public class JsonResult implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer ERROR = 1;
    public static final Integer GO_LOGIN = 2;

    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
