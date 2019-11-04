package com.example.cse5236app.repository;

import com.example.cse5236app.dao.FolderDao;
import com.example.cse5236app.dao.WordDao;
import com.example.cse5236app.db.AppDatabase;
import com.example.cse5236app.model.Word;
import com.example.cse5236app.model.Folder;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        wordDao = database.wordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<Word> get(int id) {
        return wordDao.findWordById(id);
    }

    public void insert(Word word){
        new InsertFolderAsyncTask(wordDao).execute(word);
    }

    public void delete(Word word){
        new DeleteFolderAsyncTask(wordDao).execute(word);
    }

    /**
     * list of all words function
     */
    public LiveData<List<Word>> getAllWords(){
        return allWords;
    }

    /**
     * insert
     */
    private static class InsertFolderAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        private InsertFolderAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.addWord(words[0]);
            return null;
        }
    }

    /**
     * delete
     */
    private static class DeleteFolderAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        private DeleteFolderAsyncTask(WordDao wordDao) { this.wordDao = wordDao;}

        @Override
        protected Void doInBackground(Word... words){
            wordDao.delete(words[0]);
            return null;
        }
    }

}
