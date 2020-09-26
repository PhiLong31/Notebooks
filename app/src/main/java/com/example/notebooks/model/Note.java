package com.example.notebooks.model;

public class Note {
    private String title;
    private String timeCreate;
    private String lastTimeUpdated;
    private String content;

    public Note(String title, String timeCreate, String lastTimeUpdated, String content) {
        this.title = title;
        this.timeCreate = timeCreate;
        this.lastTimeUpdated = lastTimeUpdated;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public String getLastTimeUpdated() {
        return lastTimeUpdated;
    }

    public String getContent() {
        return content;
    }
}
