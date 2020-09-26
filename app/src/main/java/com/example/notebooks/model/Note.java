package com.example.notebooks.model;

public class Note {
    private String documentId;
    private String title;
    private String timeCreate;
    private String lastTimeUpdated;
    private String content;

    public Note(String title , String content, String timeCreate, String lastTimeUpdated) {
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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
