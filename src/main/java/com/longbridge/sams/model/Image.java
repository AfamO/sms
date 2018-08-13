package com.longbridge.sams.model;

import javax.persistence.Entity;

@Entity
public class Image extends AbstractEntity{

    private String name;

    private String path;

    private String comment;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
