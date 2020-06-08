package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "channel", strict = false)
public class Channel {

    @Element(name = "image", required = false)
    private Image image;
    @Element(name = "copyright", required = false)
    private String copyright;
    @ElementList(name = "item", inline = true)
    private List<Article> articles;
    @Element(name = "lastBuildDate", required = false)
    private String lastBuildDate;
    @Element(name = "link", required = false)
    private String link;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "language", required = false)
    private String language;
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "pubDate", required = false)
    private String pubDate;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
