package com.aimright.admin.demoapp1.Fragments;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.adimoro.business.adimorosdk.view.InfomoAdView;
import com.aimright.admin.demoapp1.Activities.NewsActivity;
import com.aimright.admin.demoapp1.Activities.SplashScreen;
import com.aimright.admin.demoapp1.Adapters.Adapter;

import com.aimright.admin.demoapp1.Models.fetchUrl.DataUrl;
import com.aimright.admin.demoapp1.Models.fetchUrl.FetchUrl;
import com.aimright.admin.demoapp1.Models.fetchUrl.ResultsFetchUrl;
import com.aimright.admin.demoapp1.Utils.DataUtils;
/*import com.thedascapital.www.newsapp.R;*/
import com.aimright.admin.demoapp1.apiutils.ApiClient;
import com.aimright.admin.demoapp1.apiutils.ApiClient1;
import com.aimright.admin.demoapp1.apiutils.ApiInterface;
import com.aimright.admin.demoapp1.Models.Article;
import com.aimright.admin.demoapp1.Models.News;
import com.aimright.admin.demoapp1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String API_KEY = "aa7887707b8c429293ec7f73068ec202";
    //private final SearchView searchView;
    private final String fetch_url1;
    private final InfomoSDK infomoSDK13;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private List<Article> articlesSearch = new ArrayList<>();
    private Adapter adapter;
    private String TAG = FirstFragment.class.getSimpleName();
    private SwipeRefreshLayout refreshLayout;
   // TextView newsHeadline;
    int val;
    private RelativeLayout errorLayout;
    private Button connectionRetryBtn;
    private TextView errorTitle, errorMessage;
    private ImageView errorImage;
    private InfomoSDK infomoSDK1;
    private FetchUrl fetchUrl;
    private ConstraintLayout delayLayout;
    private TextView noDataFound;
    //private InfomoSDK infomoSdk;

    // private InfomoSDK infomoSDK;
    public FirstFragment(InfomoSDK behavior, String fetch_url, InfomoSDK infomoSDK12) {
        // Required empty public constructor
       // infomoSDK=behavior;
        //searchView=searchView1;
        fetch_url1=fetch_url;
        infomoSDK13=infomoSDK12;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        delayLayout=view.findViewById(R.id.delayLayout);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        noDataFound=view.findViewById(R.id.newsHeadline);
      //  newsHeadline = view.findViewById(R.id.newsHeadline);

        errorLayout = view.findViewById(R.id.errorLayout);
        connectionRetryBtn = view.findViewById(R.id.connectionRetryBtn);
        errorMessage = view.findViewById(R.id.errorMessage);
        errorTitle = view.findViewById(R.id.errorTitle);
        errorImage = view.findViewById(R.id.errorImage);

        InfomoAdView bottomBanner = view.findViewById(R.id.adViewBottom);
        bottomBanner.refreshAd();

        fetchUrl=new FetchUrl();

        if (fetch_url1.equalsIgnoreCase("News"))
        {
            LoadJson(fetch_url1);
        }
        else if (fetch_url1.equalsIgnoreCase("US Headlines"))
        {
            LoadJson(fetch_url1);
        }
        else
        {
            LoadFetchUrl();
        }

        infomoSDK1= SplashScreen.getReference1();

         val = getArguments().getInt("val");
        boolean  isChecked=NewsActivity.isChecked();
        if (val == 1  && isChecked){
          //  infomoSDK13.showAd();
           // onLoadingRefresh("News");
        }else if (val == 2){
         //   infomoSDK1.showAd();

          //  onLoadingRefresh("US Headlines");

        } else {

           // onLoadingRefresh("BBC Headlines");
        }





        return view;
    }


    public void LoadFetchUrl(){


        ApiInterface apiInterface = ApiClient.getApiClient("").create(ApiInterface.class);

        errorLayout.setVisibility(View.GONE);

      //  refreshLayout.setRefreshing(true);
       // newsHeadline.setText("Getting The Result");


        Call<FetchUrl> call;

            call = apiInterface.getFetchUrl(fetch_url1);
            call.enqueue(new Callback<FetchUrl>() {
                @Override
                public void onResponse(Call<FetchUrl> call, Response<FetchUrl> response) {
                    if (response.body() != null){
                        delayLayout.setVisibility(View.GONE);
                        noDataFound.setVisibility(View.GONE);

                        if (!articles.isEmpty()){
                            articles.clear();
                        }

                        fetchUrl=response.body();
                        settingAdapterFetchUrl(fetchUrl, ""/*, fetchUrl*/);
                        //settingAdapter(articles);

                       // newsHeadline.setText("Top HeadLines");
                        refreshLayout.setRefreshing(false);
                       // newsHeadline.setVisibility(View.VISIBLE);

                     //   initListerner("News");

                    }else {
                        noDataFound.setVisibility(View.VISIBLE);

                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"No Result", Toast.LENGTH_SHORT).show();
                       // newsHeadline.setVisibility(View.INVISIBLE);

                        String errorCode;
                        switch (response.code()){
                            case 404:
                                errorCode = "No data found";
                                break;
                            case 500:
                                errorCode = "Server Error";
                                break;
                            default:
                                errorCode = "Unknown error";
                                break;
                        }

                        showErrorMessage(R.drawable.ic_satellite_signal,
                                "No Result",
                                "Please try again later\n"+ errorCode);

                    }
                }

                @Override
                public void onFailure(Call<FetchUrl> call, Throwable t) {
                    delayLayout.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(),"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                  //  newsHeadline.setVisibility(View.INVISIBLE);

                    showErrorMessage(R.drawable.ic_satellite_signal,
                            "No Result",
                            "Network failure, Please try again later \n"+t.getLocalizedMessage().toString());
                }
            });





    }



    public void LoadJson(String countryVal){
        ApiInterface apiInterface = ApiClient1.getApiClient("news").create(ApiInterface.class);

        errorLayout.setVisibility(View.GONE);

     //   refreshLayout.setRefreshing(true);
      //  newsHeadline.setText("Getting The Result");

        String country = DataUtils.getCountry();

        Call<News> call;
       // call = apiInterface.getNewsCC("in",countryVal, API_KEY);

            if (countryVal.equalsIgnoreCase("News"))
            {
                call = apiInterface.getNewsCC();
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.body() != null){
                            noDataFound.setVisibility(View.GONE);

                            delayLayout.setVisibility(View.GONE);
                            if (!articles.isEmpty()){
                                articles.clear();
                            }

                            articles = response.body().getArticles();
                            List<ResultsFetchUrl> resultsFetchUrls=new ArrayList<>();

                            for (int i =0; i<articles.size();i++)
                            {
                                ResultsFetchUrl resultsFetchUrl=new ResultsFetchUrl();
                                resultsFetchUrl.setAuthor(articles.get(i).getAuthor());
                                    String[] strings=new String[1];
                                    strings[0] =articles.get(i).getUrlToImage();
                                resultsFetchUrl.setThumbnails(strings);

                                resultsFetchUrl.setTime_stamp(articles.get(i).getPublishedAt());

                                resultsFetchUrl.setNews_title(articles.get(i).getTitle());
                                resultsFetchUrl.setShare_url(articles.get(i).getUrl());

                                resultsFetchUrls.add(resultsFetchUrl);

                            }
                            DataUrl dataUrl=new DataUrl();
                            dataUrl.setResults(resultsFetchUrls);
                            fetchUrl.setData(dataUrl);
                            settingAdapterFetchUrl(fetchUrl, "News"/*, fetchUrl*/ );

                          //  newsHeadline.setText("Top HeadLines");
                            refreshLayout.setRefreshing(false);
                         //   newsHeadline.setVisibility(View.VISIBLE);

                           // initListerner("News");

                        }else {
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(),"No Result", Toast.LENGTH_SHORT).show();
                          //  newsHeadline.setVisibility(View.INVISIBLE);
                            noDataFound.setVisibility(View.VISIBLE);
                            String errorCode;
                            switch (response.code()){
                                case 404:
                                    errorCode = "No data found";
                                    break;
                                case 500:
                                    errorCode = "Server Error";
                                    break;
                                default:
                                    errorCode = "Unknown error";
                                    break;
                            }

                            showErrorMessage(R.drawable.ic_satellite_signal,
                                    "No Result",
                                    "Please try again later\n"+ errorCode);

                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                     //   newsHeadline.setVisibility(View.INVISIBLE);
                        delayLayout.setVisibility(View.GONE);
                        showErrorMessage(R.drawable.ic_satellite_signal,
                                "No Result",
                                "Network failure, Please try again later \n"+t.getLocalizedMessage().toString());
                    }
                });
            }
            else if (countryVal.equalsIgnoreCase("US Headlines"))
            {
                 call = apiInterface.getNewsUS();
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    if (response.body() != null){
                        delayLayout.setVisibility(View.GONE);
                        if (!articles.isEmpty()){
                            articles.clear();
                        }
                        noDataFound.setVisibility(View.GONE);

                        articles = response.body().getArticles();

                        List<ResultsFetchUrl> resultsFetchUrls=new ArrayList<>();

                        for (int i =0; i<articles.size();i++)
                        {
                            ResultsFetchUrl resultsFetchUrl=new ResultsFetchUrl();
                            resultsFetchUrl.setAuthor(articles.get(i).getAuthor());
                            String[] strings=new String[1];
                            strings[0] =articles.get(i).getUrlToImage();
                            resultsFetchUrl.setThumbnails(strings);

                            resultsFetchUrl.setTime_stamp(articles.get(i).getPublishedAt());

                            resultsFetchUrl.setNews_title(articles.get(i).getTitle());
                            resultsFetchUrl.setShare_url(articles.get(i).getUrl());

                            resultsFetchUrls.add(resultsFetchUrl);

                        }
                        DataUrl dataUrl=new DataUrl();
                        dataUrl.setResults(resultsFetchUrls);
                        fetchUrl.setData(dataUrl);

                        settingAdapterFetchUrl(fetchUrl, "US Headlines"/*, fetchUrl*/);

                     //   newsHeadline.setText("Top HeadLines");
                        refreshLayout.setRefreshing(false);
                     //   newsHeadline.setVisibility(View.VISIBLE);

                        //initListerner("US Headlines");

                    }else {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"No Result", Toast.LENGTH_SHORT).show();
                      //  newsHeadline.setVisibility(View.INVISIBLE);
                        noDataFound.setVisibility(View.VISIBLE);
                        String errorCode;
                        switch (response.code()){
                            case 404:
                                errorCode = "No data found";
                                break;
                            case 500:
                                errorCode = "Server Error";
                                break;
                            default:
                                errorCode = "Unknown error";
                                break;
                        }

                        showErrorMessage(R.drawable.ic_satellite_signal,
                                "No Result",
                                "Please try again later\n"+ errorCode);

                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    refreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(),"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                 //   newsHeadline.setVisibility(View.INVISIBLE);
                    delayLayout.setVisibility(View.GONE);
                    showErrorMessage(R.drawable.ic_satellite_signal,
                            "No Result",
                            "Network failure, Please try again later \n"+t.getLocalizedMessage().toString());
                }
            });
            }
          /*  else if (countryVal.equalsIgnoreCase("BBC Headlines"))
            {
                //call = apiInterface.getNewsBBC();
                call = apiInterface.getNewsBBC("bbc-news", "aa7887707b8c429293ec7f73068ec202" );
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.body() != null){

                            if (!articles.isEmpty()){
                                articles.clear();
                            }

                            articles = response.body().getArticles();
                            settingAdapter(articles);

                            newsHeadline.setText("Top HeadLines");
                            refreshLayout.setRefreshing(false);
                            newsHeadline.setVisibility(View.VISIBLE);

                            initListerner("BBC Headlines");

                        }else {
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(getContext(),"No Result", Toast.LENGTH_SHORT).show();
                            newsHeadline.setVisibility(View.INVISIBLE);

                            String errorCode;
                            switch (response.code()){
                                case 404:
                                    errorCode = "No data found";
                                    break;
                                case 500:
                                    errorCode = "Server Error";
                                    break;
                                default:
                                    errorCode = "Unknown error";
                                    break;
                            }

                            showErrorMessage(R.drawable.ic_satellite_signal,
                                    "No Result",
                                    "Please try again later\n"+ errorCode);

                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        newsHeadline.setVisibility(View.INVISIBLE);

                        showErrorMessage(R.drawable.ic_satellite_signal,
                                "No Result",
                                "Network failure, Please try again later \n"+t.getLocalizedMessage().toString());
                    }
                });
            }*/



    }

   /* private void settingAdapter(List<Article> articles1) {
        adapter = new Adapter(articles1, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }*/

    private void settingAdapterFetchUrl(FetchUrl articles1, String s/*, FetchUrl fetchUrl*/) {
        adapter = new Adapter(articles1, getContext(), s/*, fetchUrl*/);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showErrorMessage(int imageView, String title, String message) {
        errorLayout.setVisibility(View.VISIBLE);
        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        connectionRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (val == 1){
                    onLoadingRefresh("News");
                }else if (val == 2){
                    onLoadingRefresh("US Headlines");
                } else {
                    onLoadingRefresh("BBC Headlines");
                }
            }
        });

    }

   /* private void initListerner(String bbc_headlines){
        adapter.setOnItemClickListerner(new Adapter.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(getContext(),"Ganesha "+articles.get(position).getAuthor(), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String vData = gson.toJson(articles);
                PrefUtils.storeArticles(getActivity(), vData);
                Intent ii = new Intent(getActivity(), NewsDetailsActivity.class);

                ii.putExtra("title",bbc_headlines);
                //Article article = articles.get(position);
                ii.putExtra("position", position);
                //Bundle bundle = new Bundle();
                //bundle.putParcelableArrayList("test",  articles);
                //ii.putExtras(bundle);

               // ii.putExtra("test", (Serializable) articles);

                startActivity(ii);
            }
        });
    }*/

    @Override
    public void onRefresh() {
        if (val == 1){
            onLoadingRefresh("News");
        }else if (val == 2){
            onLoadingRefresh("US Headlines");
        } else {
            onLoadingRefresh("BBC Headlines");
        }
    }

    private void onLoadingRefresh(final String keyword){
        refreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        //LoadJson(keyword);
                        if (fetch_url1.equalsIgnoreCase("News"))
                        {
                            LoadJson(fetch_url1);
                        }
                        else if (fetch_url1.equalsIgnoreCase("US Headlines"))
                        {
                            LoadJson(fetch_url1);
                        }
                        else
                        {
                            LoadFetchUrl();
                        }

                    }
                }
        );
    }

}
