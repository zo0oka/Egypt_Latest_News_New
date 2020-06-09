package com.zookanews.egyptlatestnews.parser;

import android.annotation.SuppressLint;

import com.zookanews.egyptlatestnews.model.Article;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import timber.log.Timber;

public class SaxXmlHandler extends DefaultHandler {
    private final String categoryName;
    private final String websiteName;
    private StringBuilder tempValue = new StringBuilder();
    private List<Article> articles;
    private String link, title, description, imgSrc, guid, enclosure, thumb_url, mediaThumbnail, articleLink, articleThumbnailUrl, imageUrl, content;
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
            articles.add(new Article(title, articleLink, description, content, articlePubDate, articleThumbnailUrl,
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
                int end = tempValue.lastIndexOf("\"");
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
            try {
                pubDate = getMillisFromString(tempValue.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timber.d("Tag pubDate: %s", pubDate);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("SimpleDateFormat")
    private long getMillisFromString(String dateString) throws ParseException {
        long pubDate;
        int lettersCount = dateString.length();
        if (lettersCount == 29) {
            pubDate = ((new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")).parse(dateString.replace("GMT", "EET"))).getTime();
        } else if (lettersCount == 19) {
            pubDate = ((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateString)).getTime();
        } else if (lettersCount == 31) {
            pubDate = ((new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")).parse(dateString)).getTime();
        } else if (lettersCount == 26) {
            pubDate = ((new SimpleDateFormat("EEE , dd-MM-yyyy HH:mm:ss")).parse(dateString)).getTime();
        } else {
            pubDate = Calendar.getInstance().getTime().getTime();
        }
        return Calendar.getInstance().getTimeInMillis();
    }

    public void characters(char[] ch, int start, int length) {
        tempValue.append(ch, start, length);
    }
}
