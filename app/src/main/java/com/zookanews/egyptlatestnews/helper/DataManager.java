package com.zookanews.egyptlatestnews.helper;

import com.zookanews.egyptlatestnews.R;
import com.zookanews.egyptlatestnews.model.Category;
import com.zookanews.egyptlatestnews.model.Feed;
import com.zookanews.egyptlatestnews.model.Website;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 08 Jun, 2020.
 * Have a nice day!
 */
public class DataManager {

    public static final List<Feed> feeds = new ArrayList<Feed>() {
        {
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?typeId=2&homePage=false", Category.CategoryNames.OPINION, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=3", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=8", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=4", Category.CategoryNames.FINANCE, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=2", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=7", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=10", Category.CategoryNames.ARTS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=86", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=69", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=5", Category.CategoryNames.INVESTIGATIONS, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=6", Category.CategoryNames.CULTURE, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=9", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=22", Category.CategoryNames.TRAVEL, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=57&typeId=2", Category.CategoryNames.OPINION, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=12", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=96", Category.CategoryNames.FASHION, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=109", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=110", Category.CategoryNames.HEALTH, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.almasryalyoum.com/rss/rssfeeds?sectionId=111", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALMASRY_ALYOUM));
            add(new Feed("https://www.elwatannews.com/home/rssfeeds", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALWATAN));
            add(new Feed("https://www.dostor.org/rss.aspx", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALDOSTOUR));
            add(new Feed("https://akhbarak.net/api/top_news.rss", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AKHBARAK));
            add(new Feed("https://alwafd.news/feed/533", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/20", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/21", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/26", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/27", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/29", Category.CategoryNames.FINANCE, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/19", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/44", Category.CategoryNames.ARTS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/18", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/48", Category.CategoryNames.CULTURE, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/54", Category.CategoryNames.TRAVEL, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/37", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/34", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/49", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/103", Category.CategoryNames.FASHION, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/82", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/639", Category.CategoryNames.VIDEOS, Website.WebsiteNames.ALWAFD));
            add(new Feed("https://alwafd.news/feed/175", Category.CategoryNames.VIDEOS, Website.WebsiteNames.ALWAFD));
            add(new Feed("http://feeds.bbci.co.uk/arabic/rss.xml", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.BBC_ARABIA));
            add(new Feed("http://feeds.bbci.co.uk/arabic/middleeast/rss.xml", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.BBC_ARABIA));
            add(new Feed("https://www.rosaelyoussef.com/RSS/73", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/61", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/78", Category.CategoryNames.CULTURE, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/63", Category.CategoryNames.INVESTIGATIONS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/2109", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/81", Category.CategoryNames.HEALTH, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/67", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/71", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/68", Category.CategoryNames.ARTS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/72", Category.CategoryNames.FINANCE, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/66", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/2111", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/74", Category.CategoryNames.WOMAN, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.rosaelyoussef.com/RSS/62", Category.CategoryNames.SPORTS, Website.WebsiteNames.ROSE_ALYOUSEF));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=39", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=45", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=42", Category.CategoryNames.FINANCE, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=38", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=46", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=37", Category.CategoryNames.ARTS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=48", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=51", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=1138", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=61", Category.CategoryNames.INVESTIGATIONS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://www.elfagr.com/rss.aspx?id=41", Category.CategoryNames.VIDEOS, Website.WebsiteNames.ALFAGR));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=1", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=160", Category.CategoryNames.INVESTIGATIONS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=23", Category.CategoryNames.SPORTS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=10", Category.CategoryNames.FINANCE, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=172", Category.CategoryNames.FINANCE, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=11", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=36", Category.CategoryNames.ARTS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=33", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=32", Category.CategoryNames.WOMAN, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=35", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=173", Category.CategoryNames.TRAVEL, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=155", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=117", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://akhbarelyom.com/RSS/GetSectionNewsRSS/1?SectionID=29", Category.CategoryNames.CULTURE, Website.WebsiteNames.AKHBAR_ELYOUM));
            add(new Feed("https://www.elbalad.news/rss.aspx", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.SADA_ELBALAD));
            add(new Feed("https://www.vetogate.com/RSS/931", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/39", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/35", Category.CategoryNames.FINANCE, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/38", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/131", Category.CategoryNames.OPINION, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/34", Category.CategoryNames.SPORTS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/926", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/37", Category.CategoryNames.POLITICS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/40", Category.CategoryNames.HEALTH, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/40", Category.CategoryNames.WOMAN, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/32", Category.CategoryNames.ARTS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("https://www.vetogate.com/RSS/33", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.BAWABET_VETO));
            add(new Feed("http://almogaz.com/rss", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/politics", Category.CategoryNames.POLITICS, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/sports", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/art-culture", Category.CategoryNames.CULTURE, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/art-culture", Category.CategoryNames.ARTS, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/crime", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/economy", Category.CategoryNames.FINANCE, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("http://almogaz.com/rss/tech", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALMOGAZ));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=203", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=319", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=296", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=298", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=84", Category.CategoryNames.ARTS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=88", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=94", Category.CategoryNames.CULTURE, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=245", Category.CategoryNames.HEALTH, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=319", Category.CategoryNames.POLITICS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=297", Category.CategoryNames.FINANCE, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=332", Category.CategoryNames.SPORTS, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=286", Category.CategoryNames.WORLD_ARAB, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=89", Category.CategoryNames.WOMAN, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.youm7.com/rss/SectionRss?SectionID=328", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.ALYOUM7));
            add(new Feed("https://www.shorouknews.com/rss/main", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/egypt/rss", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Politics/arab/rss", Category.CategoryNames.POLITICS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Politics/world/rss", Category.CategoryNames.POLITICS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/sports/local-sports/rss", Category.CategoryNames.SPORTS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/sports/international/rss", Category.CategoryNames.SPORTS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/art/rss", Category.CategoryNames.ARTS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Economy/business/rss", Category.CategoryNames.FINANCE, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/sports/rss", Category.CategoryNames.SPORTS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Economy/rss", Category.CategoryNames.FINANCE, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Economy/citizines/rss", Category.CategoryNames.FINANCE, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/accidents/rss", Category.CategoryNames.ACCIDENTS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/local/rss", Category.CategoryNames.LATEST_NEWS, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/ladies/rss", Category.CategoryNames.WOMAN, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/variety/Internet-Comm/rss", Category.CategoryNames.TECHNOLOGY, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/variety/health/rss", Category.CategoryNames.HEALTH, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/people-life/trips/rss", Category.CategoryNames.TRAVEL, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/auto/rss", Category.CategoryNames.AUTOMOTIVE, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/Culture/rss", Category.CategoryNames.CULTURE, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("https://www.shorouknews.com/columns/rss", Category.CategoryNames.OPINION, Website.WebsiteNames.AL_SHOROUK));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=2&lang=1&region=1&iscompany=1", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=2&lang=1&region=2&iscompany=1", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=2&lang=1&region=1&iscompany=0", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=2&lang=1&region=2&iscompany=0", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=34&lang=1&region=1&iscompany=0", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
            add(new Feed("http://rss2.arabfinance.com/nAfLastnews.rss?ntid=34&lang=1&region=2&iscompany=0", Category.CategoryNames.FINANCE, Website.WebsiteNames.ARAB_FINANCE));
        }
    };

    public static final ArrayList<Category> categories = new ArrayList<Category>() {
        {
            add(new Category(Category.CategoryNames.LATEST_NEWS, R.id.nav_latest_news, "أخر الأخبار"));
            add(new Category(Category.CategoryNames.POLITICS, R.id.nav_politics, "الأخبار السياسية"));
            add(new Category(Category.CategoryNames.ACCIDENTS, R.id.nav_accidents, "حوادث و قضايا"));
            add(new Category(Category.CategoryNames.FINANCE, R.id.nav_finance, "إقتصاد و بنوك"));
            add(new Category(Category.CategoryNames.SPORTS, R.id.nav_sports, "أخبار الرياضة"));
            add(new Category(Category.CategoryNames.WOMAN, R.id.nav_woman, "عالم المرأة و المنوعات"));
            add(new Category(Category.CategoryNames.ARTS, R.id.nav_arts, "أخبار الفن"));
            add(new Category(Category.CategoryNames.TECHNOLOGY, R.id.nav_technology, "علوم و تكنولوجيا"));
            add(new Category(Category.CategoryNames.VIDEOS, R.id.nav_videos, "فيديوهات"));
            add(new Category(Category.CategoryNames.AUTOMOTIVE, R.id.nav_automotive, "عالم السيارات"));
            add(new Category(Category.CategoryNames.INVESTIGATIONS, R.id.nav_investigations, "تحقيقات و حوارات"));
            add(new Category(Category.CategoryNames.CULTURE, R.id.nav_culture, "الثقافة"));
            add(new Category(Category.CategoryNames.TRAVEL, R.id.nav_travel, "سياحة و طيران"));
            add(new Category(Category.CategoryNames.HEALTH, R.id.nav_health, "الصحة و اللياقة"));
            add(new Category(Category.CategoryNames.OPINION, R.id.nav_opinion, "أقلام و آراء"));
            add(new Category(Category.CategoryNames.WORLD_ARAB, R.id.nav_world_arab, "عرب وعالم"));
            add(new Category(Category.CategoryNames.FASHION, R.id.nav_fashion, "الموضة"));
            add(new Category(Category.CategoryNames.CORONA, R.id.nav_corona, "فيروس كورونا"));
        }
    };

    public static final List<Website> websites = new ArrayList<Website>() {
        {
            add(new Website(Website.WebsiteNames.ALMASRY_ALYOUM, R.id.nav_almasry_alyoum, "المصرى اليوم"));
            add(new Website(Website.WebsiteNames.ALWATAN, R.id.nav_alwatan, "الوطن"));
            add(new Website(Website.WebsiteNames.ALDOSTOUR, R.id.nav_aldostour, "الدستور"));
            add(new Website(Website.WebsiteNames.AKHBARAK, R.id.nav_akhbarak, "أخبارك"));
            add(new Website(Website.WebsiteNames.ALWAFD, R.id.nav_alwafd, "بوابة الوفد الإلكترونية"));
            add(new Website(Website.WebsiteNames.BBC_ARABIA, R.id.nav_bbc_arabic, "BBC"));
            add(new Website(Website.WebsiteNames.ALFAGR, R.id.nav_alfagr, "بوابة الفجر الإلكترونية"));
            add(new Website(Website.WebsiteNames.ROSE_ALYOUSEF, R.id.nav_rose_alyousef, "جريدة روز اليوسف"));
            add(new Website(Website.WebsiteNames.AKHBAR_ELYOUM, R.id.nav_akhbar_alyoum, "أخبار اليوم"));
            add(new Website(Website.WebsiteNames.SADA_ELBALAD, R.id.nav_sada_elbalad, "صدى البلد"));
            add(new Website(Website.WebsiteNames.BAWABET_VETO, R.id.nav_bawabet_veto, "بوابة فيتو"));
            add(new Website(Website.WebsiteNames.ALMOGAZ, R.id.nav_almogaz, "الموجز"));
            add(new Website(Website.WebsiteNames.ALYOUM7, R.id.nav_alyoum7, "اليوم السابع"));
            add(new Website(Website.WebsiteNames.AL_SHOROUK, R.id.nav_alshorouk, "الشروق"));
            add(new Website(Website.WebsiteNames.ARAB_FINANCE, R.id.nav_arab_finance, "Arab Finance"));
        }
    };
}
