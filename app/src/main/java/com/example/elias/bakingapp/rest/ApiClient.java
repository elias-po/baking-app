package com.example.elias.bakingapp.rest;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.elias.bakingapp.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // ButterKnife doesn't work because the field is static
    public static String BASE_URL;
    public static Retrofit retrofit = null;

    public static Retrofit getClient(Context context, View view){
        BASE_URL = context.getString(R.string.base_url);
        if (BASE_URL.isEmpty()){
            Log.d("BASE_URL", "BASE_URL isEmpty, assigning https://d17h27t6h515a5.cloudfront.net/topher/");
            BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/";
        }

        if (retrofit == null){
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
        }

        return retrofit;
    }

}
