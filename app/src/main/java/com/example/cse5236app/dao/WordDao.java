package com.example.cse5236app.dao;

import com.example.cse5236app.model.Word;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM word_table WHERE id = :id")
    LiveData<Word> findWordById(int id);

    @Insert()
    void addWord(Word word);

    @Delete
    void delete(Word word);

}
