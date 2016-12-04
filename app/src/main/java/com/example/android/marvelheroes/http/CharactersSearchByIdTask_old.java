package com.example.android.marvelheroes.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.marvelheroes.model.Character;

import java.io.IOException;

/**
 * Created by luizramos on 05/11/16.
 */

public class CharactersSearchByIdTask_old extends AsyncTaskLoader<Character> {
    Character mCharacter ;
    String mId;

    public CharactersSearchByIdTask_old(Context context, String id) {
        super(context);
        mId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mId == null) return;
        if(mCharacter == null){
            forceLoad();
        }else{
            deliverResult(mCharacter);
        }

    }

    @Override
    public Character loadInBackground() {
        try {
            mCharacter =  CharactersParser.searchById(mId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCharacter;
    }
}
