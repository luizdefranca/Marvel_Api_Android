package com.example.android.marvelheroes.database;

import android.provider.BaseColumns;

/**
 * Created by luizramos on 05/12/16.
 */

public interface CharacterContract extends BaseColumns {

    String TABLE_NAME="characters";

    String NAME = "name";
    String DESCRIPTION = "description";
    String PATH = "path";
    String EXTENSION = "extension";
    String TYPE = "type";
    String URL = "url";
}
