package com.example.android.marvelheroes;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.marvelheroes.http.CharacterSearchByIdTask;
import com.example.android.marvelheroes.model.Character;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailCharacterFragment extends Fragment implements LoaderManager.LoaderCallbacks<Character> {

    WebView webview;
    ImageView mImageDetail;

    public static DetailCharacterFragment newInstance(String id){
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        DetailCharacterFragment detailCharacterFragment = new DetailCharacterFragment();
        detailCharacterFragment.setArguments(bundle);
        return detailCharacterFragment;
    }

    public DetailCharacterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_character, container, false);

        if(getResources().getBoolean(R.bool.phone)){
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            //Botao para o actionBar - favoritos
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

            //Método para adicionar aos favoritos
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //TODO Adicionar aos favoritos
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }



        webview = (WebView) view.findViewById(R.id.webView_content);
        mImageDetail = (ImageView) view.findViewById(R.id.image_detail);


        getLoaderManager().initLoader(5,getArguments(), this);
        return view;
    }


    @Override
    public Loader<Character> onCreateLoader(int id, Bundle args) {
        String mId = args.getString("id");
        return new CharacterSearchByIdTask(getActivity(), mId);
    }


    @Override
    public void onLoadFinished(Loader<Character> loader, Character character) {
        if(character != null){
            if (getResources().getBoolean(R.bool.phone)){
                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
                        getView().findViewById(R.id.toolbar_layout);


                appBarLayout.setTitle(character.name);
            }
            String image = character.getLandScapeImage();

            Glide
                    .with(getActivity())
                    .load(image)
                    .into(mImageDetail);
            if(getResources().getBoolean(R.bool.phone)){
                webview.setInitialScale(240);

            }else{

                webview.setInitialScale(150);
            }





            String url;

            if (character.urls.get(0) != null ){
                url = character.urls.get(0).getUrl();
                webview.loadUrl(url);
            }else {
                url = "<html><body> <b>Page not found. Error 404.</b> </body></html>";
                webview.loadData(url,"txt/html", null);
            }

        }else {
            Toast.makeText(getActivity(), "Server with throuble", Toast.LENGTH_SHORT ).show();
        }

    }



    @Override
    public void onLoaderReset(Loader<Character> loader) {

    }

}
