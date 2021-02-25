package com.baher.medicata.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemApp {
    @SerializedName("data")
    @Expose
    private List<DataApp> data = null;

    public List<DataApp> getData() {
        return data;
    }

    public void setData(List<DataApp> data) {
        this.data = data;
    }
}
