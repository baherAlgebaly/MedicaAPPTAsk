package com.baher.medicata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baher.medicata.models.main.Data;
import com.baher.medicata.models.main.Item;
import com.baher.medicata.models.main.Main;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetSpeciality4Intit extends AppCompatActivity {

    Retrofit retrofit ;

    ApiInterface apiInterface ;
    TextView txtMain ;

    ImageView imgDrawer ;
    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_speciality4_intit);

        txtMain =(TextView)findViewById(R.id.txtMain);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        Call<Main> call = apiInterface.getSpecialtiesForInstit(1277,1096);

        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if (!response.isSuccessful()) {
                    // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(GetSpeciality4Intit.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Main main = response.body();

                txtMain.setText(main.getCode()+""+ "\n");
                txtMain.append(main.getMessage()+""+ "\n");

                Item item = main.getItem();
                List<Data> datas = item.getData();

                for (Data data : datas) {
                    String content = "";
                    content += " ID: " + data.getId() + "\n";
                    content += "Title: " + data.getTitle() + "\n";
                    content += "Image: " + data.getImage() + "\n";
                    content += "AppIcon: " + data.getAppIcon() + "\n\n";
                    txtMain.append(content);

                    // Toast.makeText(JsonParser.this, arrayList.size()+"", Toast.LENGTH_SHORT).show();
                }





            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {
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
                Intent intent = new Intent(GetSpeciality4Intit.this ,MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txGetSpecialty4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSpeciality4Intit.this,GetSpeciality4Intit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetDoc4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSpeciality4Intit.this,GetDoctor4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetApp4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSpeciality4Intit.this,GetAppmnt4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetTime4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSpeciality4Intit.this,getTime4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetInstitOnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSpeciality4Intit.this,InstitutionsOnMap.class);
                startActivity(intent);
            }
        });

    }
}