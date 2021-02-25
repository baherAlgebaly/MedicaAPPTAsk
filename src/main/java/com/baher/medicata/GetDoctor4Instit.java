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
import com.squareup.picasso.Picasso;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetDoctor4Instit extends AppCompatActivity {


    TextView txtName ,txtSpecDesc ,txtRate ,txtPrice ;
    ImageView imgAvatar ;
    ImageView imgDrawer ;
    private SlidingRootNav slidingRootNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_doctor4_instit);

        txtName =(TextView)findViewById(R.id.txtName);
        txtSpecDesc =(TextView)findViewById(R.id.txtSpecDesc);
        txtRate =(TextView)findViewById(R.id.txtRate);
        txtPrice =(TextView)findViewById(R.id.txtPrice);

        imgAvatar =(ImageView)findViewById(R.id.imgAvatar3);



        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://medicahealthy.net/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface  apiInterface2 = retrofit2.create(ApiInterface.class);


        Call<Main> call = apiInterface2.getDocforInstit(1277,13994,1096);

        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if (!response.isSuccessful()) {
                    // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(GetDoctor4Instit.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Main main = response.body();

                Toast.makeText(GetDoctor4Instit.this, response.code()+"", Toast.LENGTH_SHORT).show();


                Item item = main.getItem();

                List<Data> datas = item.getData();

                for (Data data : datas) {
                    Picasso.get().load(data.getImage()).into(imgAvatar);
                    txtName.setText(data.getName());
                    txtSpecDesc.setText(data.getSpecialtyDescription());
                    txtRate.setText(data.getRate()+" out of 5 ");
                    txtPrice.setText(data.getMaxPrice());

                    // Toast.makeText(JsonParser.this, arrayList.size()+"", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {


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
                Intent intent = new Intent(GetDoctor4Instit.this ,MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txGetSpecialty4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctor4Instit.this,GetSpeciality4Intit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetDoc4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctor4Instit.this,GetDoctor4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetApp4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctor4Instit.this,GetAppmnt4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetTime4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctor4Instit.this,getTime4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetInstitOnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetDoctor4Instit.this,InstitutionsOnMap.class);
                startActivity(intent);
            }
        });


    }
}