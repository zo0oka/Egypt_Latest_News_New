package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "enclosure", strict = false)
public class Enclosure {

    @Attribute(name = "length")
    private String length;
    @Attribute(name = "type")
    private String type;
    @Attribute(name = "url")
    private String url;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [length = " + length + ", type = " + type + ", url = " + url + "]";
    }
}
