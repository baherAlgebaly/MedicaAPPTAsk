package com.baher.medicata;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baher.medicata.adapters.docAdapter;
import com.baher.medicata.models.main.Data;
import com.baher.medicata.models.main.Item;
import com.baher.medicata.models.main.Main;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstitutionsOnMap extends AppCompatActivity  implements OnMapReadyCallback {


    public static GoogleMap mMap ;

    Retrofit retrofit ;
    ApiInterface apiInterface ;

    ArrayList<Data> doctors ;

    SupportMapFragment mapFragment ;

    RecyclerView recDoc ;
    docAdapter docAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions_on_map);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        recDoc = findViewById(R.id.recDoctors);
        recDoc.setLayoutManager(new LinearLayoutManager(InstitutionsOnMap.this, LinearLayoutManager.HORIZONTAL, false));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
        Call<Main> call = apiInterface.getDoctors(30.0609 ,31.219698 ,3 ,2);

        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if (!response.isSuccessful()) {
                    // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(InstitutionsOnMap.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Main main = response.body();

                Toast.makeText(InstitutionsOnMap.this, main.getMessage()+""+ "\n", Toast.LENGTH_SHORT).show();

                Item item = main.getItem();
                List<Data> datas = item.getData();
                doctors = new ArrayList<>();

                for (Data data : datas) {
                    doctors.add(data);

                }

                docAdapter = new docAdapter(InstitutionsOnMap.this,doctors,false);

                recDoc.setAdapter(docAdapter);


                mapFragment.getMapAsync(InstitutionsOnMap.this);


            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

                Toast.makeText(InstitutionsOnMap.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng myLocation = new LatLng(30.0609,31.219698) ;

     for (Data doctor : doctors ){
         LatLng docLocation = new LatLng(Double.parseDouble(doctor.getLat()),Double.parseDouble(doctor.getLng())) ;
         mMap.addMarker(new MarkerOptions().position(docLocation).title(doctor.getInstitutionTitle()).
                 icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(InstitutionsOnMap.this))));
     }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,15));
    }


    public static void moveMap (String lat ,String lng){
        LatLng docLocation = new LatLng(Double.parseDouble(lat),Double.parseDouble(lng)) ;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(docLocation,15));

    }

    public static Bitmap createCustomMarker(Context context) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_view, null);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

}