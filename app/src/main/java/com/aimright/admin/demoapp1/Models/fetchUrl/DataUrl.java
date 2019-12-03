package com.aimright.admin.demoapp1.Models.fetchUrl;

import java.util.List;

public class DataUrl {
    private Meta meta;

    private List<ResultsFetchUrl> results;

    public Meta getMeta ()
    {
        return meta;
    }

    public void setMeta (Meta meta)
    {
        this.meta = meta;
    }

    public List<ResultsFetchUrl> getResults ()
    {
        return results;
    }

    public void setResults (List<ResultsFetchUrl> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [meta = "+meta+", results = "+results+"]";
    }
}
