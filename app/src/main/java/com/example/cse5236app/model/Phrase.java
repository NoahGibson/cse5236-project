package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phrase")
public class Phrase {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "phrase")
    public String phrase;

    @ColumnInfo(name = "meaning")
    public String meaning;

}
