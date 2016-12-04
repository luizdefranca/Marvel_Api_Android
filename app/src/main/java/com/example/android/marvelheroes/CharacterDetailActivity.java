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

public class CharacterDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Character> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Botao para o actionBar - favoritos
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //Método para adicionar aos favoritos
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO Adicionar aos favoritos
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        getSupportLoaderManager().initLoader(5,getIntent().getExtras(), this);


    }


    @Override
    public Loader<Character> onCreateLoader(int id, Bundle args) {
        String mId = args.getString("id");
        return new CharacterSearchByIdTask(this, mId);
    }

    @Override
    public void onLoadFinished(Loader<Character> loader, Character character) {
        if(character != null){
            WebView webview = (WebView) findViewById(R.id.webView_content);
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(character.name);
            webview.setInitialScale(200 );
            String url;



            ImageView mImageDetail = (ImageView) findViewById(R.id.image_detail);
            if (character.urls.get(0) != null ){
                url = character.urls.get(0).getUrl();
                webview.loadUrl(url);
            }else {
                url = "<html><body> <b>Page not found. Error 404.</b> </body></html>";
                webview.loadData(url,"txt/html", null);
            }
            String image = character.getLandScapeImage();

            Glide
                    .with(this)
                    .load(image)
                    .into(mImageDetail);

        }else {
            Toast.makeText(this, "Server with throuble", Toast.LENGTH_SHORT ).show();
        }

    }



    @Override
    public void onLoaderReset(Loader<Character> loader) {

    }
}
