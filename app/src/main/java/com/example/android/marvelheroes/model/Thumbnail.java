package com.example.android.marvelheroes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luizramos on 05/11/16.
 */
public class Thumbnail implements Parcelable{

    @SerializedName("path")
    private String path;

    @SerializedName("extension")
    private String extension;

    protected Thumbnail(Parcel in) {
        setPath(in.readString());
        setExtension(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPath());
        dest.writeString(getExtension());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
