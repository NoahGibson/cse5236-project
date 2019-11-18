package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "folder_word_table",
        primaryKeys = {"folder_id", "word_id"},
        foreignKeys = {
                @ForeignKey(entity = Folder.class,
                        parentColumns = "id",
                        childColumns = "folder_id"),
                @ForeignKey(entity = Word.class,
                        parentColumns = "id",
                        childColumns = "word_id")
        })
public class FolderWord {

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "word_id")
    public int wordId;

}
