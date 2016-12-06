package com.example.android.marvelheroes;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.marvelheroes.adapters.CharacterAdapter;
import com.example.android.marvelheroes.http.CharactersSearchTask;
import com.example.android.marvelheroes.model.Character;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Character>>, SearchView.OnQueryTextListener{

    ListView mListViewCharacters;
    LoaderManager mLoaderManager;
    String mquery;
    public CharacterListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Pega a view do layout Fragment_character_list
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        //Instancia o ListView
        mListViewCharacters = (ListView) view.findViewById(R.id.list_characters);

        //Cria evento de click no ListView
        mListViewCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getActivity() instanceof OnCharacterClick ){
                    Character character = (Character) mListViewCharacters.getItemAtPosition(position);
                    ((OnCharacterClick) getActivity()).onCharacterClick(character);
                }

//                Intent intent = new Intent(getActivity(), CharacterDetailActivity.class);
//                intent.putExtra("id", Integer.toString(character.idCharacter));
//                startActivity(intent);

            }
        });

        mLoaderManager = getLoaderManager();

        mLoaderManager.initLoader(0, null, this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public Loader<List<Character>> onCreateLoader(int id, Bundle args) {
        String query =  args != null ? args.getString("q"): null;
        return new CharactersSearchTask(getActivity(), query);
    }

    @Override
    public void onLoadFinished(Loader<List<Character>> loader, List<Character> data) {
        if (data != null) {
            mListViewCharacters.setAdapter(new CharacterAdapter(getActivity(), data));

        } else{
            Toast.makeText(getActivity(), "Server with trouble!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Character>> loader) {
        mLoaderManager.initLoader(0, null, this);
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
