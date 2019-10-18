package com.example.cse5236app.db;

import android.content.Context;

import com.example.cse5236app.dao.FolderDao;
import com.example.cse5236app.dao.FolderPhraseDao;
import com.example.cse5236app.dao.FolderWordDao;
import com.example.cse5236app.dao.PhraseDao;
import com.example.cse5236app.dao.SynonymDao;
import com.example.cse5236app.dao.WordDao;
import com.example.cse5236app.model.Folder;
import com.example.cse5236app.model.FolderPhrase;
import com.example.cse5236app.model.FolderWord;
import com.example.cse5236app.model.Phrase;
import com.example.cse5236app.model.Synonym;
import com.example.cse5236app.model.Word;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        Word.class,
        Phrase.class,
        Folder.class,
        FolderWord.class,
        FolderPhrase.class,
        Synonym.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract WordDao wordDao();

    public abstract PhraseDao phraseDao();

    public abstract FolderDao folderDao();

    public abstract FolderWordDao folderWordDao();

    public abstract FolderPhraseDao folderPhraseDao();

    public abstract SynonymDao synonymDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
