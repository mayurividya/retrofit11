package com.example.retrofitclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    interface  Apiservice{
        @GET("/posts")
        Call<List<userData>> getUsers();
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    TextView body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        body = findViewById(R.id.body);


         Apiservice users = retrofit.create(Apiservice.class);

         users.getUsers().enqueue(new Callback<List<userData>>() {
             @Override
             public void onResponse(Call<List<userData>> call, Response<List<userData>> response) {
                 Log.e("usersResponse",response.body().toString());
                 body.setText(response.body().get(1).body);
             }

             @Override
             public void onFailure(Call<List<userData>> call, Throwable t) {
                 Log.e("userResponseFail",t.toString());

             }
         });
    }
}