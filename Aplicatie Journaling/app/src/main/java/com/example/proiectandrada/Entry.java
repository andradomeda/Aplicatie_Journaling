package com.example.proiectandrada;

import androidx.room.Entity;

import java.io.Serializable;

import androidx.room.PrimaryKey;
enum Mood
{
    FERICIT, TRIST;
}


@Entity(tableName = "entries")
public class Entry implements Serializable {
    @PrimaryKey(autoGenerate = false)
    private Long id;
    private String entryText;
    private Mood alegere;

    public Entry(String entryText, Mood alegere) {
        this.id = id;
        this.entryText = entryText;
        this.alegere = alegere;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public Mood getAlegere() {
        return alegere;
    }

    public void setAlegere(Mood alegere) {
        this.alegere = alegere;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryText='" + entryText + '\'' +
                ", alegere=" + alegere +
                '}';
    }
}
