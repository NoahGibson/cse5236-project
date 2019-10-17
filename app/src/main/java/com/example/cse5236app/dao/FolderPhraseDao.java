package com.example.cse5236app.dao;

//import com.example.cse5236app.model.FolderPhrase;
import com.example.cse5236app.model.Phrase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
/*
@Dao
public interface FolderPhraseDao {

    @Query("SELECT p.id, p.phrase, p.meaning FROM phrase p, folder_phrase f " +
            "WHERE f.folder_id = :folderId " +
            "AND f.phrase_id = p.id")
    List<Phrase> findAllPhrasesInFolder(int folderId);

    @Insert()
    void addPhraseToFolder(FolderPhrase folderPhrase);

    @Delete()
    void removePhraseFromFolder(FolderPhrase folderPhrase);

}
 */
