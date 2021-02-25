package com.baher.medicata.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("mobile")
    @Expose
    private Integer mobile ;
    @SerializedName("password")
    @Expose
    private Integer password ;

    public LoginRequest(Integer mobile, Integer password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
