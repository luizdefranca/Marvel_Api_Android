package com.example.android.marvelheroes.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luizramos on 05/11/16. */

public class Character {

    @SerializedName("id")
    public int idCharacter;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("thumbnail")
    public Thumbnail thumbnail;
   @SerializedName("urls")
    public ArrayList<Url> urls;

    private String mediumImage;
    private String landScapeImage;

    public String getLandScapeImage(){
        String path = thumbnail.getPath();
        String extension = thumbnail.getExtension();
        String fullAddress = path+"/landscape_large."+ extension;

        return fullAddress;
    }

    public String getMediumImage(){
        String path = thumbnail.getPath();
        String extension = thumbnail.getExtension();
        String fullAdress = path+"/portrait_medium."+ extension;

        return fullAdress;
    }

    //TODO Fazer método para pegar a url pelo tipo de retorno
    public String getUrlByType(String type){
        return null;
    }
}
