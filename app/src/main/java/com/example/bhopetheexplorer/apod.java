package com.example.bhopetheexplorer;

public class apod {
    private String date;
    private String title;
    private String explanation;
    private String media_type;
    private String url;

    public apod(String date, String title, String explanation, String media_type, String url) {
        this.date = date;
        this.title = title;
        this.explanation = explanation;
        this.media_type = media_type;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
