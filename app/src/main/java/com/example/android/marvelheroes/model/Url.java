package com.example.android.marvelheroes.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luizramos on 03/12/16.
 */
public class Url {
    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    public Url(String type, String url){
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isTypeADetail(){
        return  "detail" == type;
    }
    public boolean isTypeAWiki(){
        return  "wiki" == type;
    }
    public boolean isTypeAComicLink(){
        return  "comiclink" == type;
    }
}
