package com.example.cse5236app.dao;

import com.example.cse5236app.model.Folder;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM folder")
    List<Folder> getAll();

    @Query("SELECT * FROM folder WHERE id = :id")
    Folder findFolderById(int id);

    @Insert()
    void addFolder(Folder folder);

}
