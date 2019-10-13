package com.aimright.admin.demoapp.Models.fetchUrl;

import java.io.Serializable;

public class ResultsFetchUrl implements Serializable {
    private String featured;

    private String is_visible;

    private String time_stamp;

    private String item_type;

    private String lifespan;

    private String author;

    private String breaking_news;

    private String[] tags;

    private String is_recommended;

    private String share_url;

    private String id;

    private String news_title;

    private String[] thumbnails;

    private String slug;

    public String getFeatured ()
    {
        return featured;
    }

    public void setFeatured (String featured)
    {
        this.featured = featured;
    }

    public String getIs_visible ()
    {
        return is_visible;
    }

    public void setIs_visible (String is_visible)
    {
        this.is_visible = is_visible;
    }

    public String getTime_stamp ()
    {
        return time_stamp;
    }

    public void setTime_stamp (String time_stamp)
    {
        this.time_stamp = time_stamp;
    }

    public String getItem_type ()
    {
        return item_type;
    }

    public void setItem_type (String item_type)
    {
        this.item_type = item_type;
    }

    public String getLifespan ()
    {
        return lifespan;
    }

    public void setLifespan (String lifespan)
    {
        this.lifespan = lifespan;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getBreaking_news ()
    {
        return breaking_news;
    }

    public void setBreaking_news (String breaking_news)
    {
        this.breaking_news = breaking_news;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getIs_recommended ()
    {
        return is_recommended;
    }

    public void setIs_recommended (String is_recommended)
    {
        this.is_recommended = is_recommended;
    }

    public String getShare_url ()
    {
        return share_url;
    }

    public void setShare_url (String share_url)
    {
        this.share_url = share_url;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNews_title ()
    {
        return news_title;
    }

    public void setNews_title (String news_title)
    {
        this.news_title = news_title;
    }

    public String[] getThumbnails ()
    {
        return thumbnails;
    }

    public void setThumbnails (String[] thumbnails)
    {
        this.thumbnails = thumbnails;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [featured = "+featured+", is_visible = "+is_visible+", time_stamp = "+time_stamp+", item_type = "+item_type+", lifespan = "+lifespan+", author = "+author+", breaking_news = "+breaking_news+", tags = "+tags+", is_recommended = "+is_recommended+", share_url = "+share_url+", id = "+id+", news_title = "+news_title+", thumbnails = "+thumbnails+", slug = "+slug+"]";
    }
}
