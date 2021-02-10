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
    /*if(TextUtils.isEmpty(edPhone.getText().toString())  ){
            Toast.makeText(MainActivity.this,"Phone Required", Toast.LENGTH_LONG).show();
        }else{
            getSms(edPhone.getText().toString());
            //proceed to login
        }if (TextUtils.isEmpty(edPhone.getText().toString()) ||TextUtils.isEmpty(edSms.getText().toString()) ){
            Toast.makeText(MainActivity.this,"Phone Required", Toast.LENGTH_LONG).show();
        }else {
            login(edPhone.getText().toString(),edSms.getText().toString());
        }*/
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
                if (TextUtils.isEmpty(edPhone.getText().toString())) {
                    Toast.makeText(MainActivity.this,"Phone Required", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(edSms.getText().toString())) {
                    getSms(edPhone.getText().toString());

                }
                 else {
                    login(edPhone.getText().toString(), edSms.getText().toString());
                }
            }

            public void getSms(String phone){
                LoginRequest loginRequest = new LoginRequest(phone);


                Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this,"SMS отправлен", Toast.LENGTH_LONG).show();
                            LoginResponse loginResponse = response.body();



                        }else{
                            Toast.makeText(MainActivity.this,"Не удалось отправить SMS", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }


                });

            }

            private void login(String phone,String otp){
                LoginRequest loginRequest = new LoginRequest(phone, otp);


                Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Успешно", Toast.LENGTH_LONG).show();
                            LoginResponse loginResponse = response.body();

                        }else{
                            Toast.makeText(MainActivity.this,"Ошибка", Toast.LENGTH_LONG).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }


                });

            }
        });
    }








}