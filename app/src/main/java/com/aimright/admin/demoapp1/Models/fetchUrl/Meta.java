package com.aimright.admin.demoapp1.Models.fetchUrl;

public class Meta {
    private String last_page;

    private String limit;

    private String current_page_no;

    private String page_count;

    private String total_results;

    public String getLast_page ()
    {
        return last_page;
    }

    public void setLast_page (String last_page)
    {
        this.last_page = last_page;
    }

    public String getLimit ()
    {
        return limit;
    }

    public void setLimit (String limit)
    {
        this.limit = limit;
    }

    public String getCurrent_page_no ()
    {
        return current_page_no;
    }

    public void setCurrent_page_no (String current_page_no)
    {
        this.current_page_no = current_page_no;
    }

    public String getPage_count ()
    {
        return page_count;
    }

    public void setPage_count (String page_count)
    {
        this.page_count = page_count;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [last_page = "+last_page+", limit = "+limit+", current_page_no = "+current_page_no+", page_count = "+page_count+", total_results = "+total_results+"]";
    }


}
