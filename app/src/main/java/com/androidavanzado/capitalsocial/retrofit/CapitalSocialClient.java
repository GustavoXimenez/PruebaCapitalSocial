package com.androidavanzado.capitalsocial.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.androidavanzado.capitalsocial.common.Constants.API_CAPITALSOCIAL_BASE_URL;

public class CapitalSocialClient {

    private static CapitalSocialClient instance = null;
    private CapitalSocialService capitalSocialService;
    private Retrofit retrofit;

    public CapitalSocialClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_CAPITALSOCIAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        capitalSocialService = retrofit.create(CapitalSocialService.class);
    }

    // Patron Singleton
    public static CapitalSocialClient getInstance(){
        if(instance == null){
            instance = new CapitalSocialClient();
        }
        return instance;
    }

    public CapitalSocialService getCapitalSocialService(){
        return capitalSocialService;
    }
}
