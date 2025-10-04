package com.example.rustore.models;

public class AppItem {
    private String name;
    private String description;
    private String icon;
    private String screenshots;
    private String version;
    private String developer;
    private String downloads;


    public AppItem(String name, String description, String imageUrl, String version,
                   String author, String downloads, String screenshots) {
        this.name = name;
        this.description = description;
        this.icon = imageUrl;
        this.screenshots = screenshots;
        this.version = version;
        this.developer = author;
        this.downloads = downloads;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return icon; }
    public String getScreenshots() { return screenshots; }
    public String getVersion() { return version; }
    public String getAuthor() { return developer; }
    public String getDownloads() { return downloads; }
}
