package com.baher.medicata.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class secondData {
    @SerializedName("data")
    @Expose
    private List<Dates> data = null;

    public List<Dates> getData() {
        return data;
    }

    public void setData(List<Dates> data) {
        this.data = data;
    }
}
