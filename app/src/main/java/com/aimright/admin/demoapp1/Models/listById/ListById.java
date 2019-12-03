package com.aimright.admin.demoapp1.Models.listById;

public class ListById {
    private DataById data;

    private String status;

    public DataById getData ()
    {
        return data;
    }

    public void setData (DataById data)
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
