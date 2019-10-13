package com.aimright.admin.demoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.adimoro.business.adimorosdk.view.InfomoAdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.aimright.admin.demoapp.Models.fetchUrl.ResultsFetchUrl;
import com.aimright.admin.demoapp.Models.listById.ListById;
/*import com.thedascapital.www.newsapp.R;*/
import com.aimright.admin.demoapp.Adapters.ViewPagerAdapter3;
import com.aimright.admin.demoapp.Models.Article;
import com.aimright.admin.demoapp.Utils.PrefUtils;
import com.aimright.admin.demoapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.aimright.admin.demoapp.Fragments.myFragment3.isWebView;

public class NewsDetailsActivity extends AppCompatActivity {

    ViewPager viewPager3;
    ViewPagerAdapter3 viewPagerAdapter3;
    int position4;
    private List<Article> articles3 = new ArrayList<Article>();
    private Toolbar toolbar;
    private String Id;
    private ListById listByID;
    private int totalSize;
    private String type;
    public static int isScrolled;
    public static boolean isShown=true;
    public static int pageSelected,pageScrolled, state1;
    //  private ConstraintLayout delayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle title=getIntent().getExtras();
        getSupportActionBar().setTitle(title.getString("title"));
        //articles3 = (ArrayList<Article>) getIntent().getExtras().getParcelable("test");
        //articles3 = (ArrayList<Article>) getIntent().getSerializableExtra("test");
        position4 = getIntent().getIntExtra("position",0);
        Id=getIntent().getStringExtra("Id");
        totalSize=getIntent().getIntExtra("item_size",totalSize);
     //   delayLayout=findViewById(R.id.delayLayout);
        type=getIntent().getExtras().getString("title");
        final ArrayList<String> ss= new ArrayList<>();
        final Gson gson = new Gson();
        try {
            String storedData = PrefUtils.getArticles(NewsDetailsActivity.this);
            java.lang.reflect.Type type = new TypeToken<List<Article>>(){}.getType();
            articles3 = gson.fromJson(storedData, type);
        } catch (Exception e){
            e.printStackTrace();
        }
       // LoadFetchUrl(Id);
        InfomoAdView bannerAd=findViewById(R.id.adViewBottom);
        bannerAd.refreshAd();
        viewPager3 = findViewById(R.id.pager3);

        ArrayList<ResultsFetchUrl> articles = new ArrayList<ResultsFetchUrl>();
        articles = (ArrayList<ResultsFetchUrl>) getIntent().getSerializableExtra("articles");
        viewPagerAdapter3 = new ViewPagerAdapter3(getSupportFragmentManager(), articles3, position4, listByID, totalSize, articles, type/*, delayLayout*/);
        viewPager3.setAdapter(viewPagerAdapter3);
        viewPager3.setCurrentItem(position4);


        viewPager3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pageScrolled=position;
            }

            @Override
            public void onPageSelected(int position) {
                 pageSelected=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                state1=state;
            }
        });

    }







    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            //findViewById(R.id.webView).getVisibility() == View.VISIBLE
        if (isWebView) {
            isWebView = false;
            findViewById(R.id.webView).setVisibility(View.GONE);
           // findViewById(R.id.linearLayoutContent).setVisibility(View.VISIBLE);
            //viewPager3.setCurrentItem(viewPager3.getCurrentItem(),false);
        }else{
            super.onBackPressed();
        }

    }


}
