package com.example.notebooks.model;

public class Note {
    private String documentId;
    private String title;
    private String timeCreate;
    private String lastTimeUpdated;
    private String content;
    private String tag;

    public Note() {
    }

    public Note(String documentId, String title, String content, String timeCreate, String lastTimeUpdated, String tag) {
        this.documentId = documentId;
        this.title = title;
        this.timeCreate = timeCreate;
        this.lastTimeUpdated = lastTimeUpdated;
        this.content = content;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
