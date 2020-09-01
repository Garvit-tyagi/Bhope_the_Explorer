package com.example.bhopetheexplorer;

public class recyclerview_item{
    private String nasa_id;
    private String title;

    private String media_type;
    private String href;

    public recyclerview_item(String nasa_id, String title, String media_type, String href) {
        this.nasa_id = nasa_id;
        this.title = title;

        this.media_type = media_type;
        this.href = href;
    }

    public String getNasa_id() {
        return nasa_id;
    }

    public void setNasa_id(String nasa_id) {
        this.nasa_id = nasa_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href= href;
    }
}

