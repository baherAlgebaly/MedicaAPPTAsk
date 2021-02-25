package com.baher.medicata.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainApp {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("item")
    @Expose
    private ItemApp item;

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

    public ItemApp getItem() {
        return item;
    }

    public void setItem(ItemApp item) {
        this.item = item;
    }
}
