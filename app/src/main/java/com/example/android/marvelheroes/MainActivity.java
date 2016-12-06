package com.example.android.marvelheroes;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.marvelheroes.adapters.CharacterAdapter;
import com.example.android.marvelheroes.http.CharactersSearchTask;
import com.example.android.marvelheroes.model.Character;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements  OnCharacterClick {

    Toolbar mToolbar;
    CharacterListFragment mCharacterListFragment;
    FavoriteCharacterListFragment mFavoriteCharacterListFragment;
    ViewPager mViewPager;
    CharacterPager mCharacterPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        buildViewPager();
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//
//        mViewPager.setAdapter(new CharacterPager(getSupportFragmentManager()));
    }




    private void buildViewPager(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        mCharacterPager = new CharacterPager(getSupportFragmentManager());
        mViewPager.setAdapter(mCharacterPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    class CharacterPager extends FragmentPagerAdapter {

        public CharacterPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    if(mCharacterListFragment == null){
                        mCharacterListFragment = new CharacterListFragment();
                    }
                    return mCharacterListFragment;
                case 1:
                default:
                    if(mFavoriteCharacterListFragment == null){
                        mFavoriteCharacterListFragment = new FavoriteCharacterListFragment();
                    }
                    return mFavoriteCharacterListFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0) return "List";
            return "Favorites";

        }
    }

    @Override
    public void onCharacterClick(Character character) {
        if(getResources().getBoolean(R.bool.phone)) {
            Intent intent = new Intent(this, CharacterDetailActivity.class);
            intent.putExtra("id", Integer.toString(character.idCharacter));
            startActivity(intent);
        }else{
            DetailCharacterFragment detailCharacterFragment = DetailCharacterFragment.newInstance(Integer.toString(character.idCharacter));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_character_detail, detailCharacterFragment,  "detail")
                    .commit();
        }
    }



}
