package com.example.notebooks.Model;

import java.io.Serializable;

public class Model implements Serializable {
    private String id;
    private String title;
    private String content;

    public Model(){ }

    public Model(String id, String title, String content) {
        this.id = this.id;
        this.title = this.title;
        this.content = this.content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

