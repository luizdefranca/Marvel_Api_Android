package com.example.android.marvelheroes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.android.marvelheroes.model.Character;
import com.example.android.marvelheroes.model.Url;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by luizramos on 05/12/16.
 */

public class CharacterDAO {

    private Context mContext;

    public CharacterDAO(Context context){
        this.mContext = context;
    }
    public long inserir (Character character){
        CharacterDbHelper helper = new CharacterDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = valuesFromCharacter(character);

        long id = db.insert(CharacterContract.TABLE_NAME, null, contentValues);
        db.close();

        return  id;

    }

    @NonNull
    private ContentValues valuesFromCharacter(Character character) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CharacterContract.NAME, character.name);
        contentValues.put(CharacterContract.DESCRIPTION, character.description);
        contentValues.put(CharacterContract.PATH, character.thumbnail.getPath());
        contentValues.put(CharacterContract.EXTENSION, character.thumbnail.getExtension());
        contentValues.put(CharacterContract.TYPE, character.urls.get(0).getType());
        contentValues.put(CharacterContract.URL, character.urls.get(0).getUrl());

        return contentValues;
    }

    public long atualizar (Character character){
        CharacterDbHelper helper = new CharacterDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = valuesFromCharacter(character);

        long rowsAffected = db.update(CharacterContract.TABLE_NAME, contentValues, CharacterContract._ID +" = ?",
                new String[]{String.valueOf(character.idCharacter)});
        db.close();

        return  rowsAffected;

    }


    public long excluir(Character character){
        CharacterDbHelper helper = new CharacterDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        long rowsAffected = db.delete(CharacterContract.TABLE_NAME,
                CharacterContract._ID +" = ?",
                new String[]{String.valueOf(character.idCharacter)} );
        db.close();

        return  rowsAffected;
    }

    public List<Character> listar(){
        CharacterDbHelper helper = new CharacterDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CharacterContract.TABLE_NAME, null);
        List<Character> characters = new ArrayList<Character>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(CharacterContract._ID));
            String name = cursor.getString(cursor.getColumnIndex(CharacterContract.NAME));
            String description = cursor.getString(cursor.getColumnIndex(CharacterContract.DESCRIPTION));
            String path = cursor.getString(cursor.getColumnIndex(CharacterContract.PATH));
            String extension = cursor.getString(cursor.getColumnIndex(CharacterContract.EXTENSION));
            String type = cursor.getString(cursor.getColumnIndex(CharacterContract.TYPE));
            String url = cursor.getString(cursor.getColumnIndex(CharacterContract.URL));

            Character character = new Character();
            character.idCharacter = id;
            character.name = name;
            character.description = description;
            character.thumbnail.setPath(path);
            character.thumbnail.setExtension(extension);
            character.urls.add(0, new Url(type, url));

            characters.add(character);
        }

        cursor.close();
        db.close();

        return characters;
    }


    public Character buscaCharacterById(int id){
        CharacterDbHelper helper = new CharacterDbHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CharacterContract.TABLE_NAME + " WHERE "
                +CharacterContract._ID + " = "+ id + ";", null);

        Character character;
        if(cursor.moveToFirst()){
            character = new Character();
            int ID = cursor.getInt(cursor.getColumnIndex(CharacterContract._ID));
            String name = cursor.getString(cursor.getColumnIndex(CharacterContract.NAME));
            String description = cursor.getString(cursor.getColumnIndex(CharacterContract.DESCRIPTION));
            String path = cursor.getString(cursor.getColumnIndex(CharacterContract.PATH));
            String extension = cursor.getString(cursor.getColumnIndex(CharacterContract.EXTENSION));
            String type = cursor.getString(cursor.getColumnIndex(CharacterContract.TYPE));
            String url = cursor.getString(cursor.getColumnIndex(CharacterContract.URL));

            character.idCharacter = ID;
            character.name = name;
            character.description = description;
            character.thumbnail.setPath(path);
            character.thumbnail.setExtension(extension);
            character.urls.add(0, new Url(type, url));


        }
        db.close();
        character = null;
        return character;
    }
}
