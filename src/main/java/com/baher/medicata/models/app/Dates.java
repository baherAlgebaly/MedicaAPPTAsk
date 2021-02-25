package com.baher.medicata.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dates {
    @SerializedName("date")
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
