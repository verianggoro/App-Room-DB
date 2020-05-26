package com.pembelajar.noted.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tNoted")
public class Noted implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int noteId;

    @ColumnInfo(name = "time")
    public String timeNoted;

    @ColumnInfo(name = "title_note")
    public String titleNote;

    @ColumnInfo(name = "summary_note")
    public String summaryNote;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTimeNoted() {
        return timeNoted;
    }

    public void setTimeNoted(String timeNoted) {
        this.timeNoted = timeNoted;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getSummaryNote() {
        return summaryNote;
    }

    public void setSummaryNote(String summaryNote) {
        this.summaryNote = summaryNote;
    }
}
