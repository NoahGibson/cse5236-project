package com.example.cse5236app.dao;

import com.example.cse5236app.model.Synonym;
import com.example.cse5236app.model.Word;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SynonymDao {

    @Query("SELECT w.id, w.word, w.definition FROM word_table w, synonym_table s " +
            "WHERE s.word_id = :wordId " +
            "AND s.word_id = w.id")
    List<Word> findAllSynonymsForWord(int wordId);

    @Insert()
    void addSynonymToWord(Synonym synonym);

    @Delete()
    void removeSynonymFromWord(Synonym synonym);

}
