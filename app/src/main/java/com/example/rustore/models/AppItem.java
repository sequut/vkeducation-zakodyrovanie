package com.example.rustore.models;

public class AppItem {
    private String name;
    private String description;
    private String icon;

    public AppItem(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.icon = imageUrl;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return icon; }
}
