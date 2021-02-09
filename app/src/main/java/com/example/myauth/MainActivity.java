package com.example.myauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edPhone, edSms;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhone = findViewById(R.id.edPhone);
        edSms = findViewById(R.id.edSms);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(edPhone.getText().toString()) || TextUtils.isEmpty(edSms.getText().toString())){
                    Toast.makeText(MainActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }

            }
        });
    }


    public void login(){
        LoginRequest loginRequest = new LoginRequest();

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(MainActivity.this,DashboardActivity.class).putExtra("data",loginResponse.getPhone()));
                        }
                    },700);

                }else{
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}