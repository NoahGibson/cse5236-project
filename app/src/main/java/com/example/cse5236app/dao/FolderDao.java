package com.example.cse5236app.dao;

import com.example.cse5236app.model.Folder;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM folder_table")
    LiveData<List<Folder>> getAllFolders();

    @Query("SELECT * FROM folder_table WHERE id = :id")
    LiveData<Folder> findFolderById(int id);

    @Insert()
    void insert(Folder folder);

    @Update()
    void update(Folder folder);

    @Delete
    void delete(Folder folder);

}
