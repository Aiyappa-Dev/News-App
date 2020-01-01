package com.aimright.admin.demoapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.aimright.admin.demoapp.Activities.NewsActivity;
import com.aimright.admin.demoapp.Activities.NewsDetailsActivity;
import com.aimright.admin.demoapp.Activities.SplashScreen;
import com.aimright.admin.demoapp.Models.listById.ListById;
import com.aimright.admin.demoapp.Utils.DataUtils;
/*import com.thedascapital.www.newsapp.R;*/
import com.aimright.admin.demoapp.Models.Article;
import com.aimright.admin.demoapp.apiutils.ApiClient;
import com.aimright.admin.demoapp.apiutils.ApiInterface;
import com.aimright.admin.demoapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link myFragment3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link myFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myFragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

   // ImageView imageView3;
    TextView textView3, title;
   // ProgressBar progressBar;
    WebView webView;
  //  Button loadUrl;
    //LinearLayout linearLayoutContent;

    public static boolean isWebView = false;
    private InfomoSDK infomoSdk;
    private String contentBody;
    private String Id;
    private String type1;
    private String item_type;
    //private ConstraintLayout delayLayout;
    private int positionValue;
    private int pos;
    private boolean isShown1, isShown2=false;
    //  private boolean isShown=true;

    public myFragment3(/*ConstraintLayout delayLayout1*/int positionValue1) {
        //delayLayout=delayLayout1;
        // Required empty public constructor
        positionValue=positionValue1;
        if ((positionValue+1)%5==0 )
        {
            NewsDetailsActivity.isShown=false;
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myFragment3.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static myFragment3 newInstance(String param1, String param2) {
        myFragment3 fragment = new myFragment3(delayLayout1);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_fragment3, container, false);
if ((positionValue+1)%5!=0)
{
     pos=1;
    positionValue=1;//linearLayoutContent = view.findViewById(R.id.linearLayoutContent);
}
else
{
     pos=5;
}
/*if ((positionValue+1)%5==0 )
{
    NewsDetailsActivity.isShown=false;
}
    */   // imageView3 = view.findViewById(R.id.imageView3);
        textView3 = view.findViewById(R.id.textView3);
        title=view.findViewById(R.id.title);
       // progressBar = view.findViewById(R.id.progressLoad);
        webView = view.findViewById(R.id.webView);
       // loadUrl = view.findViewById(R.id.loadUrl);

        int val = getArguments().getInt("val");
        infomoSdk= SplashScreen.getReference1();
        boolean  isChecked=NewsActivity.isChecked();
       // delayLayout=view.findViewById(R.id.delayLayout);
       // delayLayout.setVisibility(View.VISIBLE);
       // positionValue=getArguments().getInt("positionValue")+1;


        if (NewsDetailsActivity.state1==1)
        {

        }
        if (val%5==0 && val!=0 && isChecked )

        {
            if ((positionValue+1)%5==0)
            {
                if ((positionValue+1)!=val)
                {
                    infomoSdk.showAd();
                }
                Log.e("","");
            }
            else {
                infomoSdk.showAd();

            }



      /*  if ((positionValue+1)%5==0 )
        {
            isShown1=false;
            isShown2=true;
        }
            if ((positionValue+1)%5!=0)
            {
                infomoSdk.showAd();
            }
            else if ((positionValue+1)%5==0 && (pageSelected+1)%5==0)
            {
                if (isShown1==true && isShown2==true)
                infomoSdk.showAd();
                isShown1=true;

               *//* if (NewsDetailsActivity.isShown==true)
                infomoSdk.showAd();
                NewsDetailsActivity.isShown=true;*//*

            }*/
          /*if ( pageSelected == 0&& pageScrolled==0)
          {
              infomoSdk.showAd();

          }
            if ((pos)%5!=0 )
            {
                infomoSdk.showAd();
                isShown=true;

            }
           else if ( pos==0 )
            {
                infomoSdk.showAd();
                isShown=true;
            }
           if (pos%5==0)
           {
               pos=1;
               if (!isShown)
               {
                   infomoSdk.showAd();

               }
           }*/
        }
        positionValue=1;
      //  final Article article = (Article) getArguments().getSerializable("test");
        Id=getArguments().getString("Id");
        item_type=  getArguments().getString("item_type");
        type1=getArguments().getString("type");
      //  bindData(article);



        if (type1.equalsIgnoreCase("news") || type1.equalsIgnoreCase("US Headlines"))
        {
        //    delayLayout.setVisibility(View.GONE);

           /* webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    delayLayout.setVisibility(View.GONE);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                  //  Toast.makeText(ContestActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

                }
            });*/

            webView.setVisibility(View.VISIBLE);
            String url=getArguments().getString("url");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setDomStorageEnabled(true);

            webView.setWebViewClient(new WebViewController());

          //  linearLayoutContent.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(url);
           // webView.loadDataWithBaseURL("", url, "text/html", "UTF-8", "");
        }
        else
        {
            LoadFetchUrl(Id, view);

        }



        //contentBody=getArguments().getString("webView");


       /* loadUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isWebView = true;

                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setDomStorageEnabled(true);

                webView.setWebViewClient(new WebViewController());

                linearLayoutContent.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                //webView.loadUrl(article.getUrl());
            }
        });

        loadUrl.setOnTouchListener((view1, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                loadUrl.setBackgroundColor(getResources().getColor(R.color.color_blue));
            } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                loadUrl.setBackgroundColor(getResources().getColor(R.color.konvers_incoming_call));
            }
            return false;
        });*/


        return view;

    }


    public void LoadFetchUrl(String id, View view){


        ApiInterface apiInterface = ApiClient.getApiClient("").create(ApiInterface.class);


        Call<ListById> call;

        call = apiInterface.getListByIdUrl("news/listbyid/"+id+"?version=2&item_type="+item_type);
        call.enqueue(new Callback<ListById>() {
            @Override
            public void onResponse(Call<ListById> call, Response<ListById> response) {
                if (response.body() != null && response.body().getStatus().equals("true")){
                   /* webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                          delayLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                        }
                    });
*/


                 //   delayLayout.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    String listByID = response.body().getData().getNews_body();
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setDomStorageEnabled(true);

                    webView.setWebViewClient(new WebViewController());

                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadDataWithBaseURL("", listByID, "text/html", "UTF-8", "");

                  /*  viewPagerAdapter3 = new ViewPagerAdapter3(getSupportFragmentManager(), articles3, position4, response.body());
                    viewPager3.setAdapter(viewPagerAdapter3);
                    viewPager3.setCurrentItem(position4);*/
                }
                else {
                   /* TextView status=view.findViewById(R.id.status);
                    status.setVisibility(View.VISIBLE);
                    status.setText("No Data Found");*/
                }
            }

            @Override
            public void onFailure(Call<ListById> call, Throwable t) {

             //   Toast.makeText(getActivity(),"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public myFragment3(String mParam1) {
        this.mParam1 = mParam1;
    }

    private void bindData(Article article) {

        textView3.setText(article.getContent());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(DataUtils.getRandomDrawbleColor());
        requestOptions.error(DataUtils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

       /* Glide.with(getActivity())
                .load(article.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                       // progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                       // progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView3);*/

        title.setText(article.getTitle());

    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
