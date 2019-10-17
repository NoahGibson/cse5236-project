package com.example.cse5236app.dao;

import com.example.cse5236app.model.Word;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Query("SELECT * FROM word WHERE id = :id")
    Word findWordById(int id);

    @Insert()
    void addWord(Word word);

}
