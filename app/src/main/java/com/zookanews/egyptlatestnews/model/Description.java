package com.zookanews.egyptlatestnews.model;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
public class Description {

    private String imgLink;
    private String text;

    public Description(String imgLink, String text) {
        this.imgLink = imgLink;
        this.text = text;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
