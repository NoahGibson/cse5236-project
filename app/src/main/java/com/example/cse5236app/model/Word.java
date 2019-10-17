package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "word")
    public String word;

    @ColumnInfo(name = "definition")
    public String definition;

}
