package com.baher.medicata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baher.medicata.models.login.LoginResponse;
import com.baher.medicata.models.login.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit ;
    public static User loggedUser ;

    ApiInterface apiInterface ;
    EditText edtPhone , edtPassword ;
    TextView txtLogIn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtPassword = (EditText)findViewById(R.id.edtpPassword);
        txtLogIn = (TextView)findViewById(R.id.txtLogIn);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://medicahealthy.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = "";
                String password = "";

                if (!edtPhone.getText().toString().isEmpty()){
                    password = edtPassword.getText().toString();
                    phone = edtPhone.getText().toString();
                }else {
                    password = "123456";
                    phone = "01156161776";
                }

                Call<LoginResponse> loginCall = apiInterface.Login(password,phone);

                loginCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if (!response.isSuccessful()) {
                            // textViewResult.setText("Code: " + response.code());
                            Toast.makeText(LoginActivity.this, "code : " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LoginResponse loginResponse = response.body();

                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        User user = loginResponse.getItem();


                        loggedUser = user ;
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {


                    }
                });
            }
        });






    }
}