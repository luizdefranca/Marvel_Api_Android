package com.example.android.marvelheroes.http;

import com.example.android.marvelheroes.model.Character;
import com.example.android.marvelheroes.model.Data;
import com.example.android.marvelheroes.model.JSON;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by luizramos on 05/11/16.
 */

public class CharactersParser {
    public static final String URL_SEARCH = "https://gateway.marvel.com:443/v1/public/characters?nameStartsWith=%s&limit=10&ts=12321&apikey=4ba91b7387292017df2ef6d0f93e78b3&hash=ed64bda8bdd12e45f51a6f7f1be5be98";

    public static Data searchByName(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(URL_SEARCH, query))
                .build();


        Response response = client.newCall(request).execute();

        //verifica se a resposta do servidor foi ok

        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();
            Gson gson = new Gson();

            JSON result = gson.fromJson(json, JSON.class);

            //verifica se nao retornou uma JSON nula
            if(result != null){
                return result.data;
            }
        }


        return null;
    }

}
