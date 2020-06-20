package com.zookanews.egyptlatestnews.parser;

import com.zookanews.egyptlatestnews.helper.Constants;
import com.zookanews.egyptlatestnews.model.Article;
import com.zookanews.egyptlatestnews.model.Website;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class SaxXmlHandler extends DefaultHandler {
    private final String categoryName;
    private final String websiteName;
    private StringBuilder tempValue = new StringBuilder();
    private List<Article> articles;
    private String link, title, articleDescription, description, imgSrc, guid, enclosure, thumb_url, mediaThumbnail, articleLink, articleThumbnailUrl, imageUrl, content;
    private Long pubDate;

    public SaxXmlHandler(String categoryName, String websiteName) {
        articles = new ArrayList<>();
        this.categoryName = categoryName;
        this.websiteName = websiteName;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tempValue.setLength(0);
        if (qName.equalsIgnoreCase("img")) {
            imgSrc = attributes.getValue(0);
            Timber.d("Tag img: %s", imgSrc);
        } else if (qName.equalsIgnoreCase("enclosure")) {
            enclosure = attributes.getValue("url");
            Timber.d("Tag enclosure: %s", enclosure);
        } else if (qName.equalsIgnoreCase("media:thumbnail")) {
            mediaThumbnail = attributes.getValue("url");
            Timber.d("Tag media:thumbnail: %s", mediaThumbnail);
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
            long articlePubDate;
            if (pubDate != null) {
                articlePubDate = pubDate;
            } else {
                articlePubDate = Calendar.getInstance().getTime().getTime();
            }
            if (content != null) {
                articleDescription = content;
            } else {
                articleDescription = description;
            }
            articles.add(new Article(title, articleLink, articleDescription, articlePubDate,
                    websiteName.equals(Website.WebsiteNames.ALWATAN) ? Constants.ELWATAN_THUMBNAIL_URL_PREFIX + articleThumbnailUrl : articleThumbnailUrl,
                    websiteName, categoryName, false, false));
        } else if (qName.equalsIgnoreCase("title")) {
            title = tempValue.toString();
            Timber.d("Tag Title: %s", title);
        } else if (qName.equalsIgnoreCase("link")) {
            link = tempValue.toString();
            Timber.d("Tag link: %s", link);
        } else if (qName.equalsIgnoreCase("a10:content")) {
            content = tempValue.toString();
            Timber.d("Tag content: %s", content);
        } else if (qName.equalsIgnoreCase("description")) {
            final String IMG_SRC_REG_EX = "<img src=([^>]+)>";
            final String HTML_TAG_REG_EX = "</?[^>]+>";
            if (tempValue.toString().contains("src")) {
                Timber.d("Temp: %s", tempValue.toString());
                Timber.d(String.valueOf(tempValue.indexOf("src=\"")));
                Timber.d(String.valueOf(tempValue.lastIndexOf("\"")));
                int start = tempValue.indexOf("src=\"") + 5;
                Timber.d(String.valueOf(start));
                int end = tempValue.indexOf("\"", start + 1);
                Timber.d(String.valueOf(end));
                imgSrc = tempValue.substring(start, end);
            }
            description = (tempValue.toString()).replaceFirst(IMG_SRC_REG_EX, "")
                    .replaceAll(HTML_TAG_REG_EX, "");
            Timber.d("Tag description: %s", description);
            Timber.d("Tag src: %s", imgSrc);
        } else if (qName.equalsIgnoreCase("guid")) {
            guid = tempValue.toString();
            Timber.d("Tag guid: %s", guid);
        } else if (qName.equalsIgnoreCase("thumb_url")) {
            thumb_url = tempValue.toString();
            Timber.d("Tag thumb_url: %s", thumb_url);
        } else if (qName.equalsIgnoreCase("url")) {
            imageUrl = tempValue.toString();
            Timber.d("Tag url: %s", imageUrl);
        } else if (qName.equalsIgnoreCase("pubDate")) {
            Timber.d("Received Date: %s", tempValue);
            pubDate = getMillisFromString(tempValue.toString());
            Timber.d("Tag pubDate: %s", pubDate);
        }
    }

    private long getMillisFromString(String dateString) {

        long pubDateLong = 0;


        if (dateString.length() == 27) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Timber.d("Time Format is: EEE, dd MMM yyyy HH:mm:ss Z -> Received Date: " + dateString);
            try {
                pubDateLong = sdf.parse(dateString).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (dateString.length() == 31) {

            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss '+0200'", Locale.ENGLISH);
            Timber.d("Time Format is: EEE, dd MMM yyyy HH:mm:ss '+0200' -> Received Date: " + dateString);
            try {
                pubDateLong = sdf.parse(dateString).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {

            Timber.d("Undefined Time Format -> Received Date: " + dateString);
            Calendar calendar = Calendar.getInstance();
            pubDateLong = calendar.getTimeInMillis();
        }
        return pubDateLong;
    }


    public void characters(char[] ch, int start, int length) {
        tempValue.append(ch, start, length);
    }
}
