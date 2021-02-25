package com.baher.medicata.models.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataApp {
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("time_from")
    @Expose
    private String timeFrom;
    @SerializedName("time_to")
    @Expose
    private String timeTo;
    @SerializedName("day_number")
    @Expose
    private Integer dayNumber;
    @SerializedName("dates")
    @Expose
    private secondData dates;

    //Separating for ti4Inst

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("day_name")
    @Expose
    private String dayName;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public secondData getDates() {
        return dates;
    }

    public void setDates(secondData dates) {
        this.dates = dates;
    }

    // Separating for Ti4Inst

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}

