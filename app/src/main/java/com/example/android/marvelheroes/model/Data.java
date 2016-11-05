package com.example.android.marvelheroes.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luizramos on 05/11/16.
 */

public class Data {

    @SerializedName("results")
    public ArrayList<Character> charactersList;
}
