package com.aimright.admin.demoapp.Models.fetchUrl;

public class FetchUrl {
    private DataUrl data;

    private String status;

    public DataUrl getData ()
    {
        return data;
    }

    public void setData (DataUrl data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}
