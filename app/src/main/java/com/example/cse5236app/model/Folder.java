package com.example.cse5236app.model;

import com.example.cse5236app.dao.FolderDao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "folder_table")
public class Folder {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Folder(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }


}


