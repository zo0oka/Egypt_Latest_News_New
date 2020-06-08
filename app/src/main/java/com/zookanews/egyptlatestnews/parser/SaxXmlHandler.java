package com.zookanews.egyptlatestnews.parser;

import android.annotation.SuppressLint;

import com.zookanews.egyptlatestnews.model.Article;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

public class SaxXmlHandler extends DefaultHandler {
    private StringBuilder tempValue = new StringBuilder();
    private List<Article> articles;
    private String link, title, description, imgSrc, guid, enclosure, thumb_url, mediaThumbnail, articleLink, articleThumbnailUrl, imageUrl, content;
    private String pubDate;

    public SaxXmlHandler() {
        articles = new ArrayList<>();
    }

    List<Article> getArticles() {
        return articles;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tempValue.setLength(0);
        if (qName.equalsIgnoreCase("img")) {
            imgSrc = attributes.getValue(0);
            Timber.d("Tag img: " + imgSrc);
        } else if (qName.equalsIgnoreCase("enclosure")) {
            enclosure = attributes.getValue("url");
            Timber.d("Tag enclosure: " + enclosure);
        } else if (qName.equalsIgnoreCase("media:thumbnail")) {
            mediaThumbnail = attributes.getValue("url");
            Timber.d("Tag media:thumbnail: " + mediaThumbnail);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("item")) {
            if (link != null) {
                articleLink = link;
            } else if (guid != null) {
                articleLink = guid;
            }
            if (thumb_url != null) {
                articleThumbnailUrl = thumb_url;
            } else if (imgSrc != null) {
                articleThumbnailUrl = imgSrc;
            } else if (enclosure != null) {
                articleThumbnailUrl = enclosure;
            } else if (mediaThumbnail != null) {
                articleThumbnailUrl = mediaThumbnail;
            } else if (imageUrl != null) {
                articleThumbnailUrl = imageUrl;
            }
            String articlePubDate;
            if (pubDate != null) {
                articlePubDate = pubDate;
            } else {
                articlePubDate = Calendar.getInstance().getTime().toString();
            }
        } else if (qName.equalsIgnoreCase("title")) {
            title = tempValue.toString();
            Timber.d("Tag Title: " + title);
        } else if (qName.equalsIgnoreCase("link")) {
            link = tempValue.toString();
            Timber.d("Tag link: " + link);
        } else if (qName.equalsIgnoreCase("a10:content")) {
            content = tempValue.toString();
            Timber.d("Tag content: " + content);
        } else if (qName.equalsIgnoreCase("description")) {
            final String IMG_SRC_REG_EX = "<img src=([^>]+)>";
            final String HTML_TAG_REG_EX = "</?[^>]+>";
            if (tempValue.toString().contains("src")) {
                Timber.d("Temp: " + tempValue.toString());
                Timber.d(String.valueOf(tempValue.indexOf("src=\"")));
                Timber.d(String.valueOf(tempValue.lastIndexOf("\"")));
                int start = tempValue.indexOf("src=\"") + 5;
                Timber.d(String.valueOf(start));
                int end = tempValue.lastIndexOf("\"");
                Timber.d(String.valueOf(end));
                imgSrc = tempValue.substring(start, end);
            }
            description = (tempValue.toString()).replaceFirst(IMG_SRC_REG_EX, "")
                    .replaceAll(HTML_TAG_REG_EX, "");
            Timber.d("Tag description: " + description);
            Timber.d("Tag src: " + imgSrc);
        } else if (qName.equalsIgnoreCase("guid")) {
            guid = tempValue.toString();
            Timber.d("Tag guid: " + guid);
        } else if (qName.equalsIgnoreCase("thumb_url")) {
            thumb_url = tempValue.toString();
            Timber.d("Tag thumb_url: " + thumb_url);
        } else if (qName.equalsIgnoreCase("url")) {
            imageUrl = tempValue.toString();
            Timber.d("Tag url: " + imageUrl);
        } else if (qName.equalsIgnoreCase("pubDate")) {
            pubDate = tempValue.toString();
            Timber.d("Tag pubDate: " + pubDate);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private Date getDateFromString(String dateString) throws ParseException {
        Date pubDate;
        int lettersCount = dateString.length();
        if (lettersCount == 29) {
            pubDate = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")).parse(dateString.replace("GMT", "EET"));
        } else if (lettersCount == 19) {
            pubDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateString);
        } else if (lettersCount == 31) {
            pubDate = (new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")).parse(dateString);
        } else if (lettersCount == 26) {
            pubDate = (new SimpleDateFormat("EEE , dd-MM-yyyy HH:mm:ss")).parse(dateString);
        } else {
            pubDate = (new SimpleDateFormat("EEE, MMM dd, yyyy - HH:mm")).parse(dateString);
        }
        return pubDate;
    }

    public void characters(char[] ch, int start, int length) {
        tempValue.append(ch, start, length);
    }
}
