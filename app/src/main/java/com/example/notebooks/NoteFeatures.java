package com.example.notebooks;

public interface NoteFeatures {
    void addNote(String title, String content);
    void removeNote(String idNote);
    void updateNote(String title, String content);

}
