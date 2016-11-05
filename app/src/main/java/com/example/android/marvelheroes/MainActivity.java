package com.example.android.marvelheroes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.android.marvelheroes.adapters.CharacterAdapter;
import com.example.android.marvelheroes.http.CharactersParser;
import com.example.android.marvelheroes.model.Character;
import com.example.android.marvelheroes.model.Data;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListCharacters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListCharacters = (ListView) findViewById(R.id.list_characters);
        new CharacterSearchTask().execute("spider");
    }

    class CharacterSearchTask extends AsyncTask<String, Void, List<Character>>{

        @Override
        protected List<Character> doInBackground(String... params) {
            try {
                Data data = CharactersParser.searchByName(params[0]);
                List<Character> characters = data.charactersList;
//                for(Character character: characters){
//                    Log.d("NGVL", "name --> " + character.name);
//                    Log.d("NGVL", "description --> " + character.description);
//                    Log.d("NGVL", "url --> " + character.thumbnail.path);
//                    Log.d("NGVL", "extension --> " + character.thumbnail.extension);
//
//                }
                return characters;
            } catch (IOException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Character> characters) {
            super.onPostExecute(characters);
            if (characters != null) {
                mListCharacters.setAdapter(new CharacterAdapter(MainActivity.this, characters));

            }
        }
    }
}
