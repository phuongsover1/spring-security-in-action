package com.learn_security.models;

public class Document {
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Document(String owner) {
        this.owner = owner;
    }
}
