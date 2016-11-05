package com.example.android.marvelheroes.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.marvelheroes.R;
import com.example.android.marvelheroes.model.Character;

import java.util.List;

import static com.bumptech.glide.Glide.with;

/**
 * Created by luizramos on 05/11/16.
 */

public class CharacterAdapter extends ArrayAdapter<Character>{
    public CharacterAdapter(Context context, List<Character> characters) {
        super(context, 0, characters);

    }

    String mName;
    String mDescription;
    String mPath;
    String mExtension;
    String internetUrl;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Character character = getItem(position);

        CharacterViewHolder characterViewHolder;

        if(convertView == null){
            convertView =   LayoutInflater.from(getContext()).inflate(R.layout.item_layout, null);
            characterViewHolder = new CharacterViewHolder();
            characterViewHolder.mPhoto = (ImageView) convertView.findViewById(R.id.img_photo);
            characterViewHolder.mName = (TextView) convertView.findViewById(R.id.txt_name);
            characterViewHolder.mDescription = (TextView) convertView.findViewById(R.id.txt_description);
            convertView.setTag(characterViewHolder);
        }else{
            characterViewHolder = (CharacterViewHolder) convertView.getTag();
        }

        mName = character.name;
        mDescription = character.description;
        mPath = character.thumbnail.path;
        mExtension = character.thumbnail.extension;
        internetUrl = mPath + "/portrait_medium." + mExtension;
        characterViewHolder.mName.setText(mName);
        characterViewHolder.mDescription.setText(mDescription);


        Glide
                .with(getContext())
                .load(internetUrl)
                .into(characterViewHolder.mPhoto);

        return convertView;
    }
}

class CharacterViewHolder{
    ImageView mPhoto;
    TextView mName;
    TextView mDescription;
}