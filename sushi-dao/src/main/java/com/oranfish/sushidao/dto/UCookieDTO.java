package com.oranfish.sushidao.dto;

import java.io.Serializable;
import java.util.Date;

public class UCookieDTO implements Serializable {
    private Long user_id;
    private String user_token;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
