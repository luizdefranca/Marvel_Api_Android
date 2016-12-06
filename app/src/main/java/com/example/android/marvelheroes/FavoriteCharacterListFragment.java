package com.example.android.marvelheroes;


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
import com.example.android.marvelheroes.database.CharacterDAO;
import com.example.android.marvelheroes.http.CharactersSearchTask;
import com.example.android.marvelheroes.model.Character;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteCharacterListFragment extends Fragment {

    ListView mListViewCharacters;
    LoaderManager mLoaderManager;
    String mquery;


    List<Character> mCharacter;
    CharacterDAO mDao;
    public FavoriteCharacterListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDao = new CharacterDAO(getActivity());
        mCharacter = mDao.listar();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Pega a view do layout Fragment_character_list
        View view = inflater.inflate(R.layout.fragment_favorite_character_list, container, false);

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

        mListViewCharacters.setAdapter(new CharacterAdapter(getActivity(), mCharacter));

        // Inflate the layout for this fragment
        return view;
    }



}
