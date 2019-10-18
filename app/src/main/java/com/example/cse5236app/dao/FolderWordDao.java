package com.example.cse5236app.dao;

import com.example.cse5236app.model.FolderWord;
import com.example.cse5236app.model.Word;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FolderWordDao {

    @Query("SELECT w.id, w.word, w.definition FROM word_table w, folder_word_table f " +
           "WHERE f.folder_id = :folderId " +
           "AND f.word_id = w.id")
    List<Word> findAllWordsInFolder(int folderId);

    @Insert()
    void addWordToFolder(FolderWord folderWord);

    @Delete()
    void removeWordFromFolder(FolderWord folderWord);

}
