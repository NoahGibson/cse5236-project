package com.example.cse5236app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phrase_table")
public class Phrase {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "phrase")
    public String phrase;

    @ColumnInfo(name = "meaning")
    public String meaning;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phrase(String phrase, String meaning) {
        this.phrase = phrase;
        this.meaning = meaning;
    }
}
