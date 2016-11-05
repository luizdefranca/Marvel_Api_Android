package com.example.android.marvelheroes.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.marvelheroes.model.Character;

import java.io.IOException;
import java.util.List;

/**
 * Created by luizramos on 05/11/16.
 */

public class CharactersSearchTask extends AsyncTaskLoader<List<Character>> {
    List<Character> mCharacters;
    String mQuery;

    public CharactersSearchTask(Context context, String query) {
        super(context);
        mQuery = query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mCharacters == null){
            forceLoad();;
        }else{
            deliverResult(mCharacters);
        }

    }

    @Override
    public List<Character> loadInBackground() {
        try {
            mCharacters = (List<Character>) CharactersParser.searchByName(mQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCharacters;
    }
}
