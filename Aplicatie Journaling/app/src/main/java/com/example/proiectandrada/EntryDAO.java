package com.example.proiectandrada;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface EntryDAO {


    @Insert
    void insertEntry(Entry entry);


    @Query("SELECT * FROM entries")
    List<Entry> getEntries();


    @Query("SELECT * FROM entries WHERE alegere = :mood")
    List<Entry> getEntriesByMood(Mood mood);


    @Update
    void updateEntry(Entry entry);


    @Query("DELETE FROM entries WHERE alegere = :mood")
    void deleteEntriesByMood(Mood mood);



}
