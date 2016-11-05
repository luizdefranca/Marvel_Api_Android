package com.example.android.marvelheroes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luizramos on 05/11/16.
 */
public class Thumbnail implements Parcelable{

    @SerializedName("path")
    public String path;

    @SerializedName("extension")
    public String extension;

    protected Thumbnail(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
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
}
