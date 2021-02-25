package com.baher.medicata.models.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("encode_branch_id")
    @Expose
    private String encodeBranchId;
    @SerializedName("institution_id")
    @Expose
    private Integer institutionId;
    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("institution_title")
    @Expose
    private String institutionTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("is_favorite")
    @Expose
    private String isFavorite;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("home_visit")
    @Expose
    private String homeVisit;
    @SerializedName("insurances")
    @Expose
    private Item insurances;
    @SerializedName("services")
    @Expose
    private Item services;

    //Seperating For Sp4Ins

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("app_icon")
    @Expose
    private String appIcon;

    //seprating for Doc4Instit

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("specialty_description")
    @Expose
    private String specialtyDescription;
    @SerializedName("degree")
    @Expose
    private String degree;

    @SerializedName("max_price")
    @Expose
    private String maxPrice;
    @SerializedName("min_price")
    @Expose
    private String minPrice;

    @SerializedName("preBooking")
    @Expose
    private Object preBooking;


    public Data(  String name ,String address, String image, String specialty, String price) {
        this.address = address;
        this.image = image;
        this.specialty = specialty;
        this.price = price;
        this.name = name;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getEncodeBranchId() {
        return encodeBranchId;
    }

    public void setEncodeBranchId(String encodeBranchId) {
        this.encodeBranchId = encodeBranchId;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstitutionTitle() {
        return institutionTitle;
    }

    public void setInstitutionTitle(String institutionTitle) {
        this.institutionTitle = institutionTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getHomeVisit() {
        return homeVisit;
    }

    public void setHomeVisit(String homeVisit) {
        this.homeVisit = homeVisit;
    }

    public Item getInsurances() {
        return insurances;
    }

    public void setInsurances(Item insurances) {
        this.insurances = insurances;
    }

    public Item getServices() {
        return services;
    }

    public void setServices(Item services) {
        this.services = services;
    }


    //Seperating for Sp4Inst

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    //seperating for doc4Inst

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialtyDescription() {
        return specialtyDescription;
    }

    public void setSpecialtyDescription(String specialtyDescription) {
        this.specialtyDescription = specialtyDescription;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public Object getPreBooking() {
        return preBooking;
    }

    public void setPreBooking(Object preBooking) {
        this.preBooking = preBooking;
    }
}
