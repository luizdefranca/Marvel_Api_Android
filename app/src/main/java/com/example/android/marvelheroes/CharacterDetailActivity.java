package com.example.android.marvelheroes;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.marvelheroes.http.CharacterSearchByIdTask;
import com.example.android.marvelheroes.model.Character;

public class CharacterDetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        String id = getIntent().getStringExtra("id");
        DetailCharacterFragment detailMovieFragment = DetailCharacterFragment.newInstance(id);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_character_detail, detailMovieFragment,  "detail")
                .commit();



    }



}
