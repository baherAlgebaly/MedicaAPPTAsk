package com.baher.medicata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baher.medicata.models.app.DataApp;
import com.baher.medicata.models.app.ItemApp;
import com.baher.medicata.models.app.MainApp;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getTime4Instit extends AppCompatActivity {

    TextView txtMain ;
    Retrofit retrofit ;

    ApiInterface apiInterface ;

    ImageView imgDrawer ;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time4_instit);

        txtMain = (TextView)findViewById(R.id.txtMain);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<MainApp> call = apiInterface.getTimeForInstit(1277,1444,"normal",1096,5,"28/12/2020");

        call.enqueue(new Callback<MainApp>() {
            @Override
            public void onResponse(Call<MainApp> call, Response<MainApp> response) {

                if (!response.isSuccessful()) {
                    // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(getTime4Instit.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                MainApp mainApp = response.body();
                ItemApp itemApp = mainApp.getItem();

                List<DataApp> dataApps = itemApp.getData();

                for (DataApp dataApp : dataApps) {
                    String content = "";
                    content += " Time : " + dataApp.getTime() + "\n";
                    content += "Date: " + dataApp.getDate() + "\n";
                    content += "Day Name " + dataApp.getDayName() + "\n";
                    content += "Price " + dataApp.getPrice() + "\n\n";

                    txtMain.append(content);
                }
            }

            @Override
            public void onFailure(Call<MainApp> call, Throwable t) {
                txtMain.setText(t.getMessage());

            }
        });

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        imgDrawer = (ImageView)findViewById(R.id.imgDrawer);

        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingRootNav.isMenuClosed()) {
                    slidingRootNav.openMenu();
                }else {
                    slidingRootNav.closeMenu();
                }

            }
        });

        findViewById(R.id.txtMainActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txGetSpecialty4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,GetSpeciality4Intit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetDoc4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,GetDoctor4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetApp4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,GetAppmnt4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetTime4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,getTime4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetInstitOnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getTime4Instit.this,InstitutionsOnMap.class);
                startActivity(intent);
            }
        });


    }
}