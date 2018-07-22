package com.litelogistics.app.litelogistics.network;

import com.litelogistics.app.litelogistics.app.RepositoryManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class JwtInterceptor implements Interceptor {

    /*
    * Attach JWT token to every request header
    * */
    @Override
    public Response intercept(Chain chain) throws IOException {

        String token = RepositoryManager.manager().preferences().getString(RepositoryManager.TOKEN_KEY,
                "");
        Request original = chain.request();
        if (!token.isEmpty()) {
            Request.Builder builder = original.newBuilder()
                    .addHeader("Auth", token);

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }

        return chain.proceed(original);
    }
}
