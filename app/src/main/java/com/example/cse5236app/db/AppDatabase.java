package com.example.cse5236app.db;

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
import androidx.room.RoomDatabase;

@Database(entities = {
        Word.class,
        Phrase.class,
        Folder.class,
        FolderWord.class,
        FolderPhrase.class,
        Synonym.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    public abstract PhraseDao phraseDao();

    public abstract FolderDao folderDao();

    public abstract FolderWordDao folderWordDao();

    public abstract FolderPhraseDao folderPhraseDao();

    public abstract SynonymDao synonymDao();

}
