package com.example.android.marvelheroes;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.marvelheroes.adapters.CharacterAdapter;
import com.example.android.marvelheroes.http.CharactersSearchTask;
import com.example.android.marvelheroes.model.Character;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Character>>, SearchView.OnQueryTextListener {

    ListView mListViewCharacters;
    LoaderManager mLoaderManager;
    String mquery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListViewCharacters = (ListView) findViewById(R.id.list_characters);
        mLoaderManager = getSupportLoaderManager();

        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<List<Character>> onCreateLoader(int id, Bundle args) {
        String query =  args != null ? args.getString("q"): null;
        return new CharactersSearchTask(this, query);
    }

    @Override
    public void onLoadFinished(Loader<List<Character>> loader, List<Character> data) {
        if (data != null) {
            mListViewCharacters.setAdapter(new CharacterAdapter(MainActivity.this, data));

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Character>> loader) {
        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("q", query);
        mLoaderManager.restartLoader(0,bundle, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
