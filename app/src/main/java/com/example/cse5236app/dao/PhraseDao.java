package com.example.cse5236app.dao;

import com.example.cse5236app.model.Phrase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PhraseDao {

    @Query("SELECT * FROM phrase")
    List<Phrase> getAll();

    @Query("SELECT * FROM phrase WHERE id = :id")
    Phrase findPhraseById(int id);

    @Insert()
    void addPhrase(Phrase phrase);

}
