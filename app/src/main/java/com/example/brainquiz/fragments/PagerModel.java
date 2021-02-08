package com.example.brainquiz.fragments;

public class PagerModel {

    String id, title, table;

    public PagerModel(String id, String title, String table) {
        this.id = id;
        this.title = title;
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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
}
