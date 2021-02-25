package com.baher.medicata;

import com.baher.medicata.models.app.MainApp;
import com.baher.medicata.models.login.LoginResponse;
import com.baher.medicata.models.main.Main;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en"})
    @GET("services")
    Call<Main> getDoctors(
            @Query("lat") double lat ,
            @Query("lng") double lng ,
            @Query("type") int type ,
            @Query("page") int page
    );

    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en"})
    @GET("institutions/specialties")
    Call <Main> getSpecialtiesForInstit(
            @Query("branch_id") int branchId ,
            @Query("id") int id
    );

    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en"})
    @GET("institutions/doctors")
    Call<Main> getDocforInstit (
            @Query("branch_id") int brandId,
            @Query("specialty_id") int specialityID ,
            @Query("id") int ID
    );

    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en"})
    @GET("institutions/appointments")
    Call<MainApp> getAppForInstit (
            @Query("branch_id") int branchId ,
            @Query("doctor_id") int doctorId ,
            @Query("type") String type
    );

    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en"})
    @GET("institutions/appointments/time")
    Call<MainApp> getTimeForInstit (
            @Query("branch_id") int branchId,
            @Query("doctor_id") int doctorId ,
            @Query("type") String type,
            @Query("id") int id,
            @Query("day_number") int dayNumber ,
            @Query("date") String date
    );


    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:en","Authorization:ZQC88qoDJvx7KJSYxXdrYon98hk2vA59OQa6xyZ5A9f7TigSQ4enP1gi1Hi7"})
    @POST("auth-client/login")
        // @FormUrlEncoded
    Call<LoginResponse> Login (
            @Query("password") String password ,
            @Query("mobile") String mobile
    );


    @Headers({"Accept:application/json","Content-Type:text/plain","From:c213348c8e34e7dd","User-Agent:android","Accept-Language:ar","Authorizations:xkKrXLEbSXXt9oAXrFElvmhytze2PIOhlgOm4QpAapv1uCxq3my9bv42N6iA","Institution:699"})
  //  @FormUrlEncoded
    @POST("client/reservation")
    Call <Main> MakeReservation(
            @Query("institution_branch_id") int institutionBranchId,
            @Query("institution_id") int institutionId ,
            @Query("date") String date ,
            @Query("doctor_id") int doctorId,
            @Query("timefrom") String timeFrom,
            @Query("price") int price ,
            @Query("paid_price") int paidPrice,
            @Query("type") String type
    );


}
