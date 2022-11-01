package com.example.midtermwork;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.midtermwork";
    public static final String BASEPATH = "user";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY+"/"+BASEPATH);

    public static final int PERSONS = 1;
    public static final int PERSON_ID = 2;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,BASEPATH,PERSONS);
        uriMatcher.addURI(AUTHORITY,BASEPATH+"/#",PERSON_ID);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                cursor = database.query(DatabaseHelper.TABLE_NAME, DatabaseHelper.ALL_COLUMNS, selection, selectionArgs, null, null,DatabaseHelper.ID+" ASC");
                break;
            default:
                throw new IllegalArgumentException("unknown uri" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(uriMatcher.match(uri)){
            case PERSONS:
                return "vnd.android.cursor.dir/user";
            default:
                throw new IllegalArgumentException("unknown URI" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = database.insert(DatabaseHelper.TABLE_NAME, null ,values);
        if (id >0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("fail add" + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.delete(DatabaseHelper.TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("unknown uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.update(DatabaseHelper.TABLE_NAME,contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("unknown uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
