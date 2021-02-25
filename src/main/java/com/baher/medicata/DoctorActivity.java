package com.baher.medicata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baher.medicata.models.main.Data;
import com.baher.medicata.models.main.Insurances;
import com.baher.medicata.models.main.Item;
import com.baher.medicata.models.main.Main;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.skydoves.transformationlayout.TransformationAppCompatActivity;
import com.baher.medicata.adapters.docAdapter;
import com.skydoves.transformationlayout.TransformationLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorActivity extends TransformationAppCompatActivity implements OnMapReadyCallback {
   public Data doctor ;
   public ImageView imgAvatar , imgOut ;
   public TextView txtName ,txtSpec , txtPrice ,txtAdress ,txtShowing , txtBook ;
   public TransformationLayout transLayServices , transLayInsurances , transFormedLayout ;


    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        //new AppCompatActivity().setContentView(R.layout.activity_doctor);

        Bundle bundle = getIntent().getExtras();

        txtName = (TextView)findViewById(R.id.txtName);
        txtSpec = (TextView)findViewById(R.id.txtSpec);
        txtPrice = (TextView)findViewById(R.id.txtPrice);
        txtAdress = (TextView)findViewById(R.id.txtAddress);
        txtShowing = (TextView)findViewById(R.id.txtShowing);
        txtBook = (TextView)findViewById(R.id.txtBook);

        imgAvatar = (ImageView)findViewById(R.id.imgAvatar);
        imgOut = (ImageView)findViewById(R.id.imgOut);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        transLayServices = (TransformationLayout)findViewById(R.id.transformationLayoutServices);
        transLayInsurances = (TransformationLayout)findViewById(R.id.transformationLayoutInsurances);

        doctor = docAdapter.choosenDoctor ;

        if (doctor != null){
            txtName.setText(doctor.getInstitutionTitle());
            txtSpec.setText(doctor.getSpecialty());
            txtPrice.setText(doctor.getPrice());
            txtAdress.setText(doctor.getAddress());
            Picasso.get().load(doctor.getImage()).into(imgAvatar);
        }

        Item insurances = doctor.getInsurances();
        Item services = doctor.getServices();

        mapFragment.getMapAsync(this);

        transLayServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transLayServices.startTransform();
                transFormedLayout = transLayServices ;
                List <Data> insurancesList = services.getData();
                txtShowing.setText("Services\n");
                if (insurancesList.size()!= 0){
                    for (Data insurance : insurancesList){
                        txtShowing.append(insurance.getTitle()+"\n"+insurance.getPrice()+"\n");
                    }
                }else if (insurancesList.size() == 0){
                    txtShowing.append("No Services");
                }
            }
        });

        transLayInsurances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transLayInsurances.startTransform();
                transFormedLayout = transLayInsurances ;
                List <Data> insurancesList = insurances.getData();
                txtShowing.setText("Insurances\n");
                if (insurancesList.size()!= 0){
                    for (Data insurance : insurancesList){
                        txtShowing.append(insurance.getTitle()+"\n");
                    }
                }else if (insurancesList.size() == 0){
                    txtShowing.append("No insurances");
                }
            }
        });

        imgOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (transFormedLayout != null ){
                    transFormedLayout.finishTransform();
                    transFormedLayout = null ;
                }
            }
        });


        txtBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeReservation();
            }
        });


    }


    public void MakeReservation (){
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.net/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface  apiInterface2 = retrofit2.create(ApiInterface.class);

        Call<Main> call = apiInterface2.MakeReservation(3198,2571,"04/03/2020",3822,"03:30 AM",200,200,"normal_reservation");


        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {

                Main main = response.body();
                Toast.makeText(DoctorActivity.this,main.getMessage() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
                Toast.makeText(DoctorActivity.this,t.toString() , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setScrollGesturesEnabled(false);
        LatLng docLocation = new LatLng(Double.parseDouble(doctor.getLat()),Double.parseDouble(doctor.getLng())) ;
        mMap.addMarker(new MarkerOptions().position(docLocation).title("Doctor Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(docLocation,15));

    }



}