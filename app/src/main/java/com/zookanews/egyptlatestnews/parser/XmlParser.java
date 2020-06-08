package com.zookanews.egyptlatestnews.parser;

import android.util.Xml;

import com.zookanews.egyptlatestnews.model.Description;
import com.zookanews.egyptlatestnews.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class XmlParser {

    private static final String ns = null;

    public List<Item> init(String urlString) throws IOException, XmlPullParserException {
        Timber.d("Parser Init...");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        InputStream inputStream = conn.getInputStream();
        return parse(inputStream);
    }

    public List<Item> parse(InputStream in) throws XmlPullParserException, IOException {
        Timber.d("parse: Parsing Feed");
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<Item> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Timber.d("readFeed");
        List<Item> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Timber.d("readFeed: Tag Name: " + name);
            // Starts by looking for the entry tag
            if (name.equals("channel")) {
                Timber.d("Found Channel");
                entries.addAll(readChannel(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private List<Item> readChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        Timber.d("readChannel");
        List<Item> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Timber.d("readChannel: Tag Name: " + name);
            // Starts by looking for the entry tag
            if (name.equals("item")) {
                Timber.d("Found Item");
                entries.add(readItem(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Item readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        Timber.d("readItem");
        parser.require(XmlPullParser.START_TAG, ns, "item");
        String title = null;
        Description description = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Timber.d("readItem: Tag Name: " + name);
            if (name.equals("title")) {
                Timber.d("Found Title");
                title = readTitle(parser);
                Timber.d("readItem: Title: " + title);
            } else if (name.equals("description")) {
                Timber.d("Found Description");
                readDescription(parser);
            }
//            else if (parser.getName().equals("image")) {
//                Timber.d("Found Image");
//                readImage(parser);
//            }
            else {
                skip(parser);
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "item");
        return new Item(title);
    }

    private String readImage(XmlPullParser parser) throws XmlPullParserException, IOException {
        Timber.d("readImage");
        String link = null;
        parser.require(XmlPullParser.START_TAG, ns, "image");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Timber.d("readImage: Tag Name: " + name);
            // Starts by looking for the entry tag
            if (name.equals("img")) {
                link = parser.getAttributeValue(null, "src");
                Timber.d("readImage: Image Link: " + link);
            } else {
                skip(parser);
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "image");
        return link;
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        Timber.d("readTitle: title: " + title);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    private Description readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
        final String IMG_SRC_REG_EX = "<img src=([^>]+)>";
        final String HTML_TAG_REG_EX = "</?[^>]+>";
        parser.require(XmlPullParser.START_TAG, ns, "description");
        String description = readText(parser);
        Timber.d("Description: " + description);
        String descriptionString = description.replaceFirst(IMG_SRC_REG_EX, "")
                .replaceAll(HTML_TAG_REG_EX, "");
        Timber.d("Description String: " + descriptionString);
        String imgLink = (description.substring(description.indexOf("\""), description.indexOf("/>"))).replace("\"", "");
        Timber.d("Image Link: " + imgLink);
        Timber.d("readDescriptionText: Description: " + description);
        parser.require(XmlPullParser.END_TAG, ns, "description");
        return new Description(imgLink, descriptionString);
    }

    private String readImg(XmlPullParser parser) throws IOException, XmlPullParserException {
        Timber.d("readImg");
        parser.require(XmlPullParser.START_TAG, ns, "img");
        String link = "";
        if (parser.getName().equals("img")) {
            link = parser.getAttributeValue(null, "src");
        } else {
            skip(parser);
        }
        Timber.d("readImg: Img: " + link);
//        parser.require(XmlPullParser.END_TAG, ns, "/");
        return link;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        Timber.d("readText: String: " + result);
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
