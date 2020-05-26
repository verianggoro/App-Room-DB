package com.pembelajar.noted.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pembelajar.noted.model.Noted;

import java.util.List;

@Dao
public interface NotedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Noted note);

    @Query("SELECT * from tNoted")
    LiveData<List<Noted>> getAll();

    @Query("Update tNoted set time = :time, title_note = :title, summary_note = :summary where noteId = :id ")
    void update(String time, String title, String summary, int id);

    @Delete
    void deleted(Noted noted);

}
