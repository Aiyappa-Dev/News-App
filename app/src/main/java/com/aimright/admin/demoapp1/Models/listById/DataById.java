package com.aimright.admin.demoapp1.Models.listById;

public class DataById {
    private String featured;

    private String is_visible;

    private String news_body;

    private String time_stamp;

    private String lifespan;

    private String author;

    private String breaking_news;

    private String[] videos;

    private String[] photos;

    private String[] tags;

    private String likes_count;

    private String is_recommended;

    private String highlights;

    private String share_url;

    private String comments_count;

    private String[] district;

    private String id;

    private String news_title;

    private Category[] category;

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

    public String getNews_body ()
    {
        return news_body;
    }

    public void setNews_body (String news_body)
    {
        this.news_body = news_body;
    }

    public String getTime_stamp ()
    {
        return time_stamp;
    }

    public void setTime_stamp (String time_stamp)
    {
        this.time_stamp = time_stamp;
    }

    public String  getLifespan ()
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

    public String[] getVideos ()
    {
        return videos;
    }

    public void setVideos (String[] videos)
    {
        this.videos = videos;
    }

    public String[] getPhotos ()
    {
        return photos;
    }

    public void setPhotos (String[] photos)
    {
        this.photos = photos;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getLikes_count ()
    {
        return likes_count;
    }

    public void setLikes_count (String likes_count)
    {
        this.likes_count = likes_count;
    }

    public String getIs_recommended ()
    {
        return is_recommended;
    }

    public void setIs_recommended (String is_recommended)
    {
        this.is_recommended = is_recommended;
    }

    public String getHighlights ()
    {
        return highlights;
    }

    public void setHighlights (String highlights)
    {
        this.highlights = highlights;
    }

    public String getShare_url ()
    {
        return share_url;
    }

    public void setShare_url (String share_url)
    {
        this.share_url = share_url;
    }

    public String getComments_count ()
    {
        return comments_count;
    }

    public void setComments_count (String comments_count)
    {
        this.comments_count = comments_count;
    }

    public String[] getDistrict ()
    {
        return district;
    }

    public void setDistrict (String[] district)
    {
        this.district = district;
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

    public Category[] getCategory ()
    {
        return category;
    }

    public void setCategory (Category[] category)
    {
        this.category = category;
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
        return "ClassPojo [featured = "+featured+", is_visible = "+is_visible+", news_body = "+news_body+", time_stamp = "+time_stamp+", lifespan = "+lifespan+", author = "+author+", breaking_news = "+breaking_news+", videos = "+videos+", photos = "+photos+", tags = "+tags+", likes_count = "+likes_count+", is_recommended = "+is_recommended+", highlights = "+highlights+", share_url = "+share_url+", comments_count = "+comments_count+", district = "+district+", id = "+id+", news_title = "+news_title+", category = "+category+", thumbnails = "+thumbnails+", slug = "+slug+"]";
    }
}
