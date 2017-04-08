package com.example.sads.honeycontrol.service;

import com.example.sads.honeycontrol.service.response.ResponseClient;
import com.example.sads.honeycontrol.service.response.ResponseInsertClient;
import com.example.sads.honeycontrol.service.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sads on 26/03/17.
 */
public interface ApiService {
    @POST("Login.php")
    Call<ResponseLogin> getLogin(@Query("user") String username, @Query("pass") String password);

    @POST("Client.php")
    Call<ResponseClient> getClient(@Query("id") String id, @Query("pass") String password);

    @POST("InsertClient.php")
    Call<ResponseInsertClient> insertClient(@Query("id") String id, @Query("pass") String password, @Query("name") String name);
}
