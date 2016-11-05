package com.example.android.marvelheroes;

import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.marvelheroes.adapters.CharacterAdapter;
import com.example.android.marvelheroes.http.CharactersParser;
import com.example.android.marvelheroes.http.CharactersSearchTask;
import com.example.android.marvelheroes.model.Character;
import com.example.android.marvelheroes.model.Data;

import java.io.IOException;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Character>> {

    ListView mListViewCharacters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViewCharacters = (ListView) findViewById(R.id.list_characters);
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<List<Character>> onCreateLoader(int id, Bundle args) {

        return new CharactersSearchTask(this, "iron");
    }

    @Override
    public void onLoadFinished(Loader<List<Character>> loader, List<Character> data) {
        if (data != null) {
            mListViewCharacters.setAdapter(new CharacterAdapter(MainActivity.this, data));

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Character>> loader) {

    }

}
