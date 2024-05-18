package com.example.retrofitclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class Interface {
    interface  Apiservice{
        @GET("/posts")
        Call<List<userData>> getUsers();
    }

}
