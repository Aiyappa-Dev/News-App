package com.aimright.admin.demoapp1.Models;

public class Subcategory {
    private String category_order_number;

    private String is_special;

    private String category_name;

    private String icon_url_greyscale;

    private String is_visible;

    private String icon_url_white;

    private String category_type;

    private String id;

    private Subcategory[] subcategory;

    private String fetch_url;

    public String getCategory_order_number ()
    {
        return category_order_number;
    }

    public void setCategory_order_number (String category_order_number)
    {
        this.category_order_number = category_order_number;
    }

    public String getIs_special ()
    {
        return is_special;
    }

    public void setIs_special (String is_special)
    {
        this.is_special = is_special;
    }

    public String getCategory_name ()
    {
        return category_name;
    }

    public void setCategory_name (String category_name)
    {
        this.category_name = category_name;
    }

    public String getIcon_url_greyscale ()
    {
        return icon_url_greyscale;
    }

    public void setIcon_url_greyscale (String icon_url_greyscale)
    {
        this.icon_url_greyscale = icon_url_greyscale;
    }

    public String getIs_visible ()
    {
        return is_visible;
    }

    public void setIs_visible (String is_visible)
    {
        this.is_visible = is_visible;
    }

    public String getIcon_url_white ()
    {
        return icon_url_white;
    }

    public void setIcon_url_white (String icon_url_white)
    {
        this.icon_url_white = icon_url_white;
    }

    public String getCategory_type ()
    {
        return category_type;
    }

    public void setCategory_type (String category_type)
    {
        this.category_type = category_type;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Subcategory[] getSubcategory ()
    {
        return subcategory;
    }

    public void setSubcategory (Subcategory[] subcategory)
    {
        this.subcategory = subcategory;
    }

    public String getFetch_url ()
    {
        return fetch_url;
    }

    public void setFetch_url (String fetch_url)
    {
        this.fetch_url = fetch_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [category_order_number = "+category_order_number+", is_special = "+is_special+", category_name = "+category_name+", icon_url_greyscale = "+icon_url_greyscale+", is_visible = "+is_visible+", icon_url_white = "+icon_url_white+", category_type = "+category_type+", id = "+id+", subcategory = "+subcategory+", fetch_url = "+fetch_url+"]";
    }

}
