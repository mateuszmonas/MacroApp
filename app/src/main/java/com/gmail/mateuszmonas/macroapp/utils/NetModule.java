package com.gmail.mateuszmonas.macroapp.utils;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {

    public NetModule() {
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        Interceptor authInterceprot = new AuthenticationInterceptor("tablet", "tablet123");
        return new OkHttpClient.Builder().addInterceptor(authInterceprot).build();
    }

    //login: mobile
    //haslo: mobile
    //adres: http://10.0.1.233:8080/
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://89.25.160.36:8080")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }
}

//added to okhttp as basic authentication header
class AuthenticationInterceptor implements Interceptor {

    private final String authToken;

    AuthenticationInterceptor(String username, String password) {
        String pass = username + ":" + password;
        this.authToken = "Basic " + Base64.encodeToString(pass.getBytes(), Base64.NO_WRAP);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
