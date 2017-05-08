package com.gmail.mateuszmonas.macroapp.data.remote;

import android.util.Base64;
import android.util.Log;

import com.gmail.mateuszmonas.macroapp.data.DataSource;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.data.Response;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

@Singleton
public class RemoteDataSource implements DataSource {

    private Retrofit retrofit;
    private Gson gson;
    private OkHttpClient okHttpClient;

    @Inject
    RemoteDataSource(Retrofit retrofit, Gson gson, OkHttpClient okHttpClient) {
        this.retrofit = retrofit;
        this.gson = gson;
        this.okHttpClient = okHttpClient;
    }

    @Override
    public List<Kontrahent> getKontrahenci() {
        apiEndpoint api = retrofit.create(apiEndpoint.class);
        Call<Response> response = api.getKontrahenci("{\"exec\":[{\"@id\":\"q1\",\"sql\":\"select KOD,NAZ,NIP from KH\"}]}");


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://89.25.160.36:8080/ProcExec/batch-query");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    byte[] message = ("tablet:tablet123").getBytes("UTF-8");
                    String encoded = Base64.encodeToString(message, Base64.DEFAULT);

                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("Authorization", "Basic " + encoded);

                    String str = "{\"exec\":[{\"@id\":\"q1\",\"sql\":\"select KOD,NAZ,NIP from KH\"}]}";
                    byte[] outputInBytes = str.getBytes("UTF-8");
                    OutputStream os = urlConnection.getOutputStream();
                    os.write( outputInBytes );
                    os.close();
                    InputStream in = urlConnection.getInputStream();

                    Scanner s = new Scanner(in).useDelimiter("\\A");
                    String result = s.hasNext() ? s.next() : "";

                    Log.d("asd", result);
                }catch (Exception e) {
                    Log.d("asd", "nie dziala");
                }
            }
        });
        t.start();


        return null;
    }



    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}


interface apiEndpoint{
    @POST("/")
    Call<Response> getKontrahenci(@Body String query);
}
