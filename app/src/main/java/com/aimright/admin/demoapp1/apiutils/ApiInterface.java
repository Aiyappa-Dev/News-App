package com.aimright.admin.demoapp1.apiutils;

import com.aimright.admin.demoapp1.Models.CategoryList;
import com.aimright.admin.demoapp1.Models.News;
import com.aimright.admin.demoapp1.Models.fetchUrl.FetchUrl;
import com.aimright.admin.demoapp1.Models.listById.ListById;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("top-headlines")
    Call<News> getNews(@Query("country") String country, @Query("q") String q, @Query("apiKey") String type);

    @GET("top-headlines")
    Call<News> getNewsOnlyIn(@Query("country") String country, @Query("apiKey") String type);

    /*@GET("top-headlines")
    Call<News> getNewsCC(@Query("country") String country, @Query("category") String category, @Query("apiKey") String type);*/
    @GET("everything?domains=wsj.com,nytimes.com&apiKey=aa7887707b8c429293ec7f73068ec202")
    Call<News> getNewsCC();


    @GET("top-headlines?country=us&apiKey=aa7887707b8c429293ec7f73068ec202")
    Call<News> getNewsUS();

    /*@GET("top-headlines?sources=bbc-news&apiKey=aa7887707b8c429293ec7f73068ec202")
    Call<News> getNewsBBC();*/

    @GET("top-headlines")
    Call<News> getNewsBBC(@Query("sources") String source, @Query("apiKey") String apiKey);


   /* @GET("everything")
    Call<News> getEverything(@Query("domains") String q, @Query("apiKey") String type);*/
    @GET("everything")
    Call<News> getEverything(@Query("domains") String q, @Query("apiKey") String type);


    @GET("category/list")
    Call<CategoryList> getCategoryList();


    @GET
    Call<FetchUrl> getFetchUrl(@Url String url);

    @GET
    Call<ListById> getListByIdUrl(@Url String url);


}
