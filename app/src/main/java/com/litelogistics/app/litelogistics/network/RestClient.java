package com.litelogistics.app.litelogistics.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class RestClient {



    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new JwtInterceptor()).addInterceptor(loggingInterceptor()).build();

    public static LitePayService createService() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(LitePayService.BASE_URL)
                .client(okHttpClient)
                .build();

        return retrofit.create(LitePayService.class);
    }

    private static HttpLoggingInterceptor loggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
