package com.example.android.marvelheroes.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.marvelheroes.model.Character;

import java.io.IOException;
import java.util.List;

/**
 * Created by luizramos on 05/11/16.
 */

public class CharacterSearchByIdTask extends AsyncTaskLoader<Character> {
    Character mCharacter;
    String mId;

    public CharacterSearchByIdTask(Context context, String query) {
        super(context);
        mId = query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mCharacter == null){
            forceLoad();;
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
