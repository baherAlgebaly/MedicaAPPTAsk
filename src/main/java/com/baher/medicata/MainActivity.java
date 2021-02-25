package com.baher.medicata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baher.medicata.adapters.catAdapter ;
import com.baher.medicata.models.main.Data;
import com.baher.medicata.models.main.Item;
import com.baher.medicata.models.main.Main;
import com.skydoves.transformationlayout.TransformationCompat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.baher.medicata.adapters.docAdapter;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


public class MainActivity extends AppCompatActivity {


    ImageView imgProfile  ;
    RecyclerView recCat,recDoc ;
    catAdapter catAdapter ;
    docAdapter docAdapter ;
    List<Integer> catPics ;
    List<String> catNames ;
    List<Data> doctors ;
    RecyclerView.LayoutManager recyclerViewLayoutManager ;

    TextView txtName , txtAdrss ;

    Retrofit retrofit ;

    ApiInterface apiInterface ;

    ImageView imgDrawer ;
    private SlidingRootNav slidingRootNav;

    TextView txtMenu ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransformationCompat.onTransformationStartContainer(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        //Picasso.get().load("https://scontent.fcai1-2.fna.fbcdn.net/v/t1.0-1/p240x240/119211046_621157595255130_3210340206802302036_n.jpg?_nc_cat=106&ccb=3&_nc_sid=dbb9e7&_nc_ohc=Nk24ew01d6gAX_FHM9s&_nc_ht=scontent.fcai1-2.fna&tp=6&oh=c759b67e8652b75f7f3fac85cb428a03&oe=605B5DD6").into(imgProfile);
        txtName = (TextView)findViewById(R.id.txtName);
        txtAdrss = (TextView)findViewById(R.id.txtAdrss);

        if (LoginActivity.loggedUser != null){
            Picasso.get().load(LoginActivity.loggedUser.getImage()).into(imgProfile);
            txtName.setText("Hi "+LoginActivity.loggedUser.getName());
            txtAdrss.setText(LoginActivity.loggedUser.getAddress());
        }




        recCat = (RecyclerView) findViewById(R.id.recCat);
        recDoc = (RecyclerView)findViewById(R.id.recDoc);
        catPics = new ArrayList<>();


        catPics.add(R.drawable.heartmdpi);
        catPics.add(R.drawable.stomachmdpi);
        catPics.add(R.drawable.toothmdpi);
        catPics.add(R.drawable.eyemdpi);


        catNames = new ArrayList<>();
        catNames.add("Cardiology");
        catNames.add("Internist ");
        catNames.add("Dentist");
        catNames.add("ophthalmology");


        recCat.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        catAdapter = new catAdapter(catPics, catNames);
        recCat.setAdapter(catAdapter);


        recDoc.setLayoutManager(new LinearLayoutManager(this));

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

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
                Intent intent = new Intent(MainActivity.this ,MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txGetSpecialty4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetSpeciality4Intit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetDoc4Instit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetDoctor4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetApp4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GetAppmnt4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetTime4Intit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,getTime4Instit.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtGetInstitOnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InstitutionsOnMap.class);
                startActivity(intent);
            }
        });





        listBranches();




    }


    public void listBranches(){
        Call<Main> call = apiInterface.getDoctors(30.0609 ,31.219698 ,3 ,2);

        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                if (!response.isSuccessful()) {
                    // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(MainActivity.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Main main = response.body();

                Toast.makeText(MainActivity.this, main.getMessage()+""+ "\n", Toast.LENGTH_SHORT).show();

                Item item = main.getItem();
                List <Data> datas = item.getData();
                doctors = new ArrayList<>();

                for (Data data : datas) {
                    doctors.add(data);

                    // Toast.makeText(JsonParser.this, arrayList.size()+"", Toast.LENGTH_SHORT).show();
                }


                docAdapter = new docAdapter(MainActivity.this,doctors,true);

                recDoc.setAdapter(docAdapter);



            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}