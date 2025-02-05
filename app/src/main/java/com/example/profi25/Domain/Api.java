package com.example.profi25.Domain;

import com.example.profi25.Data.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("token")
    Call<ResponseBody> signin(@Query("grant_type") String grant_type, @Header("apikey") String apikey, @Header("Content-Type") String contentType, @Body User user);
}
