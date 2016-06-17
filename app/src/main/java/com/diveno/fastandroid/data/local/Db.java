package com.diveno.fastandroid.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.diveno.fastandroid.data.model.Repo;

/**
 * Created by Diego on 15/06/2016.
 */
public class Db {

    public Db() {
    }

    public abstract static class RepoTable {
        public static final String TABLE_NAME = "repo_table";
        public static final String COLUMN_NAME = "name";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME + " TEXT PRIMARY KEY, " +
                        " ); ";

        public static ContentValues toContentValues(Repo repo) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, repo.getName());
            return values;
        }

        public static Repo parseCursor(Cursor cursor) {
            Repo profile = new Repo();
            profile.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            return profile;
        }
    }
}
