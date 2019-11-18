package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "folder_phrase_table",
        primaryKeys = {"folder_id", "phrase_id"},
        foreignKeys = {
                @ForeignKey(entity = Folder.class,
                        parentColumns = "id",
                        childColumns = "folder_id"),
                @ForeignKey(entity = Phrase.class,
                        parentColumns = "id",
                        childColumns = "phrase_id")
        })
public class FolderPhrase {

    @ColumnInfo(name = "folder_id")
    public int folderId;

    @ColumnInfo(name = "phrase_id")
    public int phraseId;

}
