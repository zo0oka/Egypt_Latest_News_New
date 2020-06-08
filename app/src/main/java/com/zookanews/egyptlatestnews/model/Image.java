package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "image", strict = false)
public class Image {

    @Element(name = "link", required = false)
    private String link;
    @Element(name = "width", required = false)
    private String width;
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "url", required = false)
    private String url;
    @Element(name = "height", required = false)
    private String height;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ClassPojo [link = " + link + ", width = " + width + ", title = " + title + ", url = " + url + ", height = " + height + "]";
    }
}
