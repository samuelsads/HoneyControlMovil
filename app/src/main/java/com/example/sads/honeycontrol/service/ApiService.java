package com.example.sads.honeycontrol.service;

import com.example.sads.honeycontrol.service.response.ResponseClient;
import com.example.sads.honeycontrol.service.response.ResponseDeleteClient;
import com.example.sads.honeycontrol.service.response.ResponseDeleteProduct;
import com.example.sads.honeycontrol.service.response.ResponseInsertClient;
import com.example.sads.honeycontrol.service.response.ResponseInsertProduct;
import com.example.sads.honeycontrol.service.response.ResponseLogin;
import com.example.sads.honeycontrol.service.response.ResponseProduct;

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
    Call<ResponseInsertClient> insertClient(@Query("id") String id, @Query("pass") String password, @Query("name") String name,@Query("father_surname") String father, @Query("mother_surname") String mother);

    @POST("deleteClient.php")
    Call<ResponseDeleteClient> deleteClient(@Query("id") String id, @Query("pass") String password, @Query("idClient") int idClient);

    @POST("Product.php")
    Call<ResponseProduct> getProduct(@Query("id") String id, @Query("pass") String password);

    @POST("insertProduct.php")
    Call<ResponseInsertProduct> insertProduct(@Query("id") String id, @Query("pass") String pass , @Query("size") String size, @Query("price") String price);

    @POST("deleteProduct.php")
    Call<ResponseDeleteProduct> deleteProduct(@Query("id") String id, @Query("pass") String pass, @Query("idProduct") int idProduct);
}
