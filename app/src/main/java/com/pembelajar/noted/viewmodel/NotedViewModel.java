package com.pembelajar.noted.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pembelajar.noted.model.Noted;
import com.pembelajar.noted.repository.NotedRepository;

import java.util.List;

public class NotedViewModel extends AndroidViewModel {

    private NotedRepository notedRepository;
    private LiveData<List<Noted>> allNoted;

    public NotedViewModel(@NonNull Application application) {
        super(application);
        notedRepository = new NotedRepository(application);
        allNoted = notedRepository.getAllNoted();
    }

    public LiveData<List<Noted>> getAllNoted(){
        return allNoted;
    }

    public void insert(Noted noted){
        notedRepository.insertNoted(noted);
    }

    public void update(String time, String title, String summary, int id){
        notedRepository.updateNoted(time, title, summary, id);
    }

    public void deletedNoted(Noted noted){
        notedRepository.deletedNote(noted);
    }

}
