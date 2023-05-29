package com.if4a.kampusproject.API;

import com.if4a.kampusproject.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();
    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("Nama") String nama,
            @Field("Akreditasi") String akreditasi,
            @Field("Motto") String motto,
            @Field("Alamat") String alamat,
            @Field("Deskripsi_kampus") String deskripsi
    );
    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("Nama") String nama,
            @Field("Akreditasi") String akreditasi,
            @Field("Motto") String motto,
            @Field("Alamat") String alamat,
            @Field("Deskripsi_kampus") String deskripsi
    );
    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );
}
