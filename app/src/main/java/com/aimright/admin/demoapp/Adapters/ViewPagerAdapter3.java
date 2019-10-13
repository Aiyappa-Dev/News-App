package com.aimright.admin.demoapp.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aimright.admin.demoapp.Fragments.myFragment3;
import com.aimright.admin.demoapp.Models.Article;
import com.aimright.admin.demoapp.Models.fetchUrl.ResultsFetchUrl;
import com.aimright.admin.demoapp.Models.listById.ListById;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter3 extends FragmentPagerAdapter {

    private  String type1;
    private List<Article> articles4 = new ArrayList<Article>();
    int positionValue;
    ArrayList<ResultsFetchUrl> articles11=new ArrayList<>();
    ListById body1;
   private int totalSize1;
   //ConstraintLayout delayLayout1;
    public ViewPagerAdapter3(@NonNull FragmentManager fm) {
        super(fm);
    }

    /*public ViewPagerAdapter3(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
*/
    public ViewPagerAdapter3(FragmentManager fm, List<Article> articles, int positionVal, ListById body, int totalSize, ArrayList<ResultsFetchUrl> articles1, String type/*, ConstraintLayout delayLayout*/) {
        //super(fm);
        super(fm);
        articles4 = articles;
        positionValue = positionVal;
        body1=body;
        totalSize1=totalSize;
        articles11=articles1;
        //delayLayout1=delayLayout;
        type1=type;
    }





    @NonNull
    @Override
    public Fragment getItem(int position) {
        myFragment3 myFragment3 = new myFragment3(/*delayLayout1*/positionValue);
        //position = position + 1;
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("val", position);
            bundle.putString("Message", "Fragment: "+position);
            if (articles11.get(position).getId()!=null)
            {
                bundle.putString("Id",articles11.get(position).getId().toString());
                bundle.putString("item_type",articles11.get(position).getItem_type());

            }
            bundle.putString("url",articles11.get(position).getShare_url());
            bundle.putString("type",type1);
            bundle.putInt("positionValue",positionValue);
            //bundle.putString("webView",body1.getData().getNews_body());
            //  Article aa = articles4.get(position);
            //   bundle.putSerializable("test", (Serializable) aa);
            myFragment3.setArguments(bundle);
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return myFragment3;
    }


    @Override
    public int getCount() {
        return articles11.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        position = position + 1;
//        if(position == 1) return "Business";
//        else if(position == 2) return "Sports";
//        else return "Science";
//    }
}
