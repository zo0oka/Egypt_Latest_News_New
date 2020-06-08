package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 07 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "img", strict = false)
public class Img {

    @Attribute(name = "src", required = false)
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
