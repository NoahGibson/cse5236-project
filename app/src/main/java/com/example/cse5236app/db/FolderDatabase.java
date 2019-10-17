package com.example.cse5236app.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cse5236app.dao.FolderDao;
import com.example.cse5236app.model.Folder;


@Database(entities = Folder.class, version = 1)
public abstract class FolderDatabase extends RoomDatabase {

    private static FolderDatabase instance;

    public abstract FolderDao folderDao();

    public static synchronized FolderDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FolderDatabase.class, "folder_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
}


}
