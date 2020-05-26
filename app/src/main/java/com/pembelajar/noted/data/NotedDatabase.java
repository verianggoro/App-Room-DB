package com.pembelajar.noted.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pembelajar.noted.model.Noted;

@Database(entities = {Noted.class}, version = 1)
public abstract class NotedDatabase extends RoomDatabase {
    public abstract NotedDao notedDao();
    private static volatile NotedDatabase INSTANCE;

    public static NotedDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotedDatabase.class, "note_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
