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


@Database(entities = Folder.class, version = 1, exportSchema = false)
public abstract class FolderDatabase extends RoomDatabase {

    private static FolderDatabase instance;

    public abstract FolderDao folderDao();

    public static synchronized FolderDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FolderDatabase.class, "folder_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
        }
        return instance;
}


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private FolderDao folderDao;

        private PopulateDbAsyncTask(FolderDatabase db){
            folderDao = db.folderDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            folderDao.insert(new Folder("title 1"));
            folderDao.insert(new Folder("title 2"));
            folderDao.insert(new Folder("title 3"));
            return null;
        }

    }

}
