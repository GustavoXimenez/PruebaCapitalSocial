package com.androidavanzado.capitalsocial.retrofit;

import com.androidavanzado.capitalsocial.retrofit.request.RequestLogin;
import com.androidavanzado.capitalsocial.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CapitalSocialService {

    @POST("register")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

}
