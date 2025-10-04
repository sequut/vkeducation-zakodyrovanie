package com.example.rustore.models;

public class AppItem {
    private String name;
    private String description;
    private int imageRes;

    public AppItem(String name, String description, int imageRes) {
        this.name = name;
        this.description = description;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }
}
