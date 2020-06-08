package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "image", strict = false)
public class ArticleImage {

    @Element(name = "link", required = false)
    private String link;
    @Element(name = "alt", required = false)
    private String alt;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
