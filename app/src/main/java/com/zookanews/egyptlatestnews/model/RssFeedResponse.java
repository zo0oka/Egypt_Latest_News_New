package com.zookanews.egyptlatestnews.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 06 Jun, 2020.
 * Have a nice day!
 */
@Root(name = "rss", strict = false)
public class RssFeedResponse {

    @Element(name = "channel", required = false)
    private Channel channel;

    @Attribute(name = "version", required = false)
    private String version;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ClassPojo [channel = " + channel + ", version = " + version + "]";
    }
}
