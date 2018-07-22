package com.litelogistics.app.litelogistics.network;

import com.litelogistics.app.litelogistics.models.BaseModel;
import com.litelogistics.app.litelogistics.models.LocationDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public interface LitePayService {

    public static final String BASE_URL = "https://kwiiki.com/";

    @GET("pfi")
    Call<BaseModel<LocationDetail>> get(@Query("pfi") String pfi);
}
