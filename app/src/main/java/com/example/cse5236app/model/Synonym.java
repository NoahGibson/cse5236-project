package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "synonym",
        primaryKeys = { "word_id", "synonym_id" },
        foreignKeys = {
                @ForeignKey(entity = Folder.class,
                        parentColumns = "id",
                        childColumns = "word_id"),
                @ForeignKey(entity = Word.class,
                        parentColumns = "id",
                        childColumns = "synonym_id")
        })
public class Synonym {

    @ColumnInfo(name = "word_id")
    public int wordId;

    @ColumnInfo(name = "synonym_id")
    public int synonymId;

}
