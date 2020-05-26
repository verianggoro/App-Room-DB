package com.pembelajar.noted.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pembelajar.noted.data.NotedDao;
import com.pembelajar.noted.data.NotedDatabase;
import com.pembelajar.noted.model.Noted;

import java.util.List;

public class NotedRepository {
    private NotedDao notedDao;
    private LiveData<List<Noted>> allNoted;

    public NotedRepository(Application app){
        NotedDatabase db = NotedDatabase.getDatabase(app);
        notedDao = db.notedDao();
    }

    public LiveData<List<Noted>> getAllNoted(){
        allNoted = notedDao.getAll();
        return allNoted;
    }

    public void insertNoted(final Noted noted){
        notedDao.insert(noted);
    }

    public void updateNoted(String time, String title, String summary, int id){
        notedDao.update(time, title, summary, id);
    }

    public void deletedNote(final Noted noted){
        notedDao.deleted(noted);
    }

}
