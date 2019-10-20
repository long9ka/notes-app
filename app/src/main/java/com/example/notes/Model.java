package com.example.notes;

import java.util.Date;

public class Model {
    private String title;
    private String notes;
    private Date time;
    private String tag;

    Model(String title, String notes, Date time, String tag) {
        this.title = title;
        this.notes = notes;
        this.time = time;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
