package com.aimright.admin.demoapp1.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.aimright.admin.demoapp1.Fragments.FirstFragment;
import com.aimright.admin.demoapp1.Models.CategoryList;
import com.aimright.admin.demoapp1.Models.Data;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   // private final SearchView searchView1;
    private final InfomoSDK infomoSDK12;
    private Data[] categoryList1;
    /*public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }*/
    InfomoSDK behavior;
    private FirstFragment firstFragment;

   /* public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        //this.behavior=behavior;
      //
    }*/

    public ViewPagerAdapter(FragmentManager supportFragmentManager, CategoryList categoryList, InfomoSDK infomoSDK11) {
        super(supportFragmentManager);
       // searchView1=searchView;
        categoryList1= categoryList.getData();
        infomoSDK12=infomoSDK11;
        //this.behavior= (InfomoSDK) infomoSDK;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (categoryList1[position].getCategory_name().equalsIgnoreCase("News"))
        {
            firstFragment = new FirstFragment(behavior, "News", infomoSDK12);
        }
        else if (categoryList1[position].getCategory_name().equalsIgnoreCase("US Headlines"))
        {
            firstFragment = new FirstFragment(behavior, "US Headlines", infomoSDK12);
        }
        else
        {
            firstFragment = new FirstFragment(behavior, categoryList1[position].getFetch_url(), infomoSDK12);
        }
        position = position + 1;
        Bundle bundle = new Bundle();
        bundle.putInt("val", position);
        bundle.putString("Message", "Fragment: "+position);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Override
    public int getCount() {
        return categoryList1.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //position = position + 1;
      /*  ArrayList<Data> data=new ArrayList<>();
        data.addAll(categoryList1.getData());*/
        return categoryList1[position].getCategory_name();
       /* if(position == 1) return "News";
        else if(position == 2) return "US Headlines";
        else return "BBC Headlines";*/
    }
}
