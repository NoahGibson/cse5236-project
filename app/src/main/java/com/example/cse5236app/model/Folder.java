package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class Folder {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

}
