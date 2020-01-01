package com.aimright.admin.demoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.adimoro.business.adimorosdk.interfaces.NotifyOperationComplete;
import com.adimoro.business.adimorosdk.receiver.CallReceiver;
import com.adimoro.business.adimorosdk.utils.LogE;
import com.adimoro.business.adimorosdk.utils.SharedPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.aimright.admin.demoapp.Adapters.Adapter;
import com.aimright.admin.demoapp.Models.CategoryList;
/*import com.thedascapital.www.newsapp.R;*/
import com.aimright.admin.demoapp.Adapters.ViewPagerAdapter;
import com.aimright.admin.demoapp.Models.Article;
import com.aimright.admin.demoapp.apiutils.ApiClient;
import com.aimright.admin.demoapp.apiutils.ApiInterface;
import com.aimright.admin.demoapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements NotifyOperationComplete {

    public static final String API_KEY = "17a75d7742bcc60b207c178e4eeeec4714b3c767";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = UserProfile.class.getSimpleName();
    Toolbar toolbar;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
   // ConstraintLayout delayLayout;
    private RelativeLayout errorLayout;
    private Button connectionRetryBtn;
    private TextView errorTitle, errorMessage;
    private ImageView errorImage;
    ProgressBar searchProgressBar;
    NestedScrollView nestedScrollView5;
    RelativeLayout searchingLayout;

   // public static InfomoSDK infomoSDK1;
    private SharedPreference pref;
    private String deviceID;
    private RelativeLayout searchResultLayout;

   // SearchView searchView;
    Switch switchAds;
    private String fcmToken;
    boolean doubleBackToExitPressedOnce = false;

  /*  Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            delayLayout.setVisibility(View.GONE);
        }
    };*/
    private CategoryList categoryList;
    public static boolean isCheckedSwitch=true;
    private MenuItem adsView;
    private InfomoSDK infomoSDK1;
    private String statusText="start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

       // delayLayout = findViewById(R.id.delayLayout);
        switchAds=findViewById(R.id.switch_ads);
        switchAds.setVisibility(View.VISIBLE);

        //handler.postDelayed(runnable, 2000); //2 seconds for splash screen
       // infomoSDK=getIntent().getSerializableExtra("infomoSDK");
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        ImageView liveTv=findViewById(R.id.live_tv);
        liveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewsActivity.this, Youtube.class );
                startActivity(intent);
            }
        });

       // initialiseInfomo();
        //askforPermissions();
        /**/
        switchAds.setChecked(true);


        switchAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchAds.isChecked())
                {
                    statusText="start";
                }
                else
                {
                    statusText="stop";
                }

                AlertDialog.Builder dialog1=new AlertDialog.Builder(NewsActivity.this);
                dialog1.setMessage("Are you sure. You want to "+ statusText +" seeing the Ads.");
                //   dialog.setTitle("Infomo");

                dialog1.setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                if (switchAds.isChecked())
                                {
                                    isCheckedSwitch=true;
                                    adsView.setTitle("Stop Seeing Ads");

                                    /*To start the ads*/
                                    PackageManager pm  = NewsActivity.this.getPackageManager();
                                    ComponentName componentName = new ComponentName(NewsActivity.this, CallReceiver.class);
                                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                            PackageManager.DONT_KILL_APP);

                                }
                                else
                                {
                                    isCheckedSwitch=false;
                                    adsView.setTitle("Start Seeing Ads");

                                    /*To stop the ads*/
                                    PackageManager pm  = NewsActivity.this.getPackageManager();
                                    ComponentName componentName = new ComponentName(NewsActivity.this, CallReceiver.class);
                                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                            PackageManager.DONT_KILL_APP);

                                }
                                //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                dialog1.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
                        if (switchAds.isChecked())
                        {
                            switchAds.setChecked(false);

                        }
                        else
                        {
                            switchAds.setChecked(true);

                        }
                        //  which1 =which;
                        dialog.dismiss();

                        // switchAds.setChecked();

                    }
                });
                AlertDialog alertDialog=dialog1.create();

                alertDialog.show();
            }
        });

        /*switchAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                //isCheckedSwitch=isChecked;


            }
        });*/


        searchResultLayout = findViewById(R.id.searchResultLayout);
        searchingLayout = findViewById(R.id.searchingLayout);
        searchProgressBar = findViewById(R.id.searchProgressBar);
        nestedScrollView5 = findViewById(R.id.nestedScrollView5);

        viewPager = findViewById(R.id.pager);

        categoryList= new CategoryList();

        infomoSDK1= SplashScreen.getReference1();
        loadCategoryList(infomoSDK1);



        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        errorLayout = findViewById(R.id.errorLayout);
        connectionRetryBtn = findViewById(R.id.connectionRetryBtn);
        errorMessage = findViewById(R.id.errorMessage);
        errorTitle = findViewById(R.id.errorTitle);
        errorImage = findViewById(R.id.errorImage);

        recyclerView = findViewById(R.id.recyclerView5);
        layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        String NOTIFICATION_CHANEL_ID = "MyNotification";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANEL_ID, "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d(TAG, msg);
                       // Toast.makeText(NewsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void loadCategoryList(InfomoSDK infomoSDK11) {
        try {
            ApiInterface apiInterface = ApiClient.getApiClient("").create(ApiInterface.class);
            Call<CategoryList> call;
            call = apiInterface.getCategoryList();
            call.enqueue(new Callback<CategoryList>() {
                @Override
                public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                    if (response.body() != null){
                            categoryList=response.body();
                        LogE.printMe(categoryList.getData().toString());
                        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), categoryList, infomoSDK11);
                        viewPager.setAdapter(viewPagerAdapter);
                    }else {


                    }
                }

                @Override
                public void onFailure(Call<CategoryList> call, Throwable t) {
                 //   Toast.makeText(NewsActivity.this,"No Result: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_menu, menu);
       // searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();


         adsView = menu.findItem(R.id.logout);


        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);


      //  searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      //  searchView.setQueryHint("Search latest news.....");
       /* searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView title=findViewById(R.id.title);
                title.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                TextView title=findViewById(R.id.title);
                title.setVisibility(View.VISIBLE);
                return false;
            }
        });*/

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)){
                    //listView clear;
                    viewPager.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);
                    searchResultLayout.setVisibility(View.VISIBLE);
                    hideSoftKeyboard();
                    LoadJson(query);
                }else {
                    //add data
                    Toast.makeText(getApplicationContext(),query, Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }




        });*/





       /* searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean newViewFocus)
            {
                if (!newViewFocus)
                {
                    //Collapse the action item.
                    //searchItem.collapseActionView();
                    //Clear the filter/search query.

                    searchResultLayout.setVisibility(View.GONE);

                    viewPager.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);

                    searchingLayout.setVisibility(View.VISIBLE);
                    nestedScrollView5.setVisibility(View.GONE);
                }
                else
                {
                    TextView title=findViewById(R.id.title);
                    title.setVisibility(View.GONE);
                }

            }
        });*/


        return true;
    }


    public static boolean isChecked()
    {
        return isCheckedSwitch;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                if (adsView.getTitle().equals("Stop Seeing Ads"))
                {
                    statusText="stop";
                }
                else
                {
                    statusText="Start";
                }

                AlertDialog.Builder dialog1=new AlertDialog.Builder(NewsActivity.this);
                dialog1.setMessage("Are you sure. You want to "+ statusText +" seeing the Ads.");
                //   dialog.setTitle("Infomo");

                dialog1.setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                if (adsView.getTitle().equals("Stop Seeing Ads"))
                                {
                                    isCheckedSwitch=false;
                                    switchAds.setChecked(false);
                                    adsView.setTitle("Start Seeing Ads");
                                    /*To stop the ads*/
                                    PackageManager pm  = NewsActivity.this.getPackageManager();
                                    ComponentName componentName = new ComponentName(NewsActivity.this, CallReceiver.class);
                                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                            PackageManager.DONT_KILL_APP);
                                }
                                else if (adsView.getTitle().equals("Start Seeing Ads"))
                                {
                                    isCheckedSwitch=true;
                                    switchAds.setChecked(true);
                                    adsView.setTitle("Stop Seeing Ads");
                                    /*To start the ads*/
                                    PackageManager pm  = NewsActivity.this.getPackageManager();
                                    ComponentName componentName = new ComponentName(NewsActivity.this, CallReceiver.class);
                                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                            PackageManager.DONT_KILL_APP);
                                }
                                dialog.dismiss();
                            }
                        });
                dialog1.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
                        if (adsView.getTitle().equals("Stop Seeing Ads"))
                        {
                            switchAds.setChecked(true);
                        }
                        else if (adsView.getTitle().equals("Start Seeing Ads"))
                        {
                            switchAds.setChecked(false);
                        }
                        //  which1 =which;
                        dialog.dismiss();

                        // switchAds.setChecked();

                    }
                });
                AlertDialog alertDialog=dialog1.create();

                alertDialog.show();





                /*To stop the ads*/
              /*  PackageManager pm  = NewsActivity.this.getPackageManager();
                ComponentName componentName = new ComponentName(NewsActivity.this, CallReceiver.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);*/


               /* FirebaseAuth.getInstance().signOut();

                // Google sign out
                GoogleSignIn.getClient(
                        getApplicationContext(),
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                ).signOut();

                Intent a = new Intent(NewsActivity.this, UserProfile.class);
                startActivity(a);*/
                return true;
          /*  case R.id.add_preference:
                *//*Intent intent=new Intent(NewsActivity.this,AdPreferences.class);
                startActivity(intent);*//*
                return true;*/
            case R.id.action_search:
                return true;

            case R.id.about_app:
                Intent intent=new Intent(NewsActivity.this, AboutApplication.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }



  /*  public void LoadJson(String q){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //String country = DataUtils.getCountry();

        Call<News> call;
        //call = apiInterface.getNews("in","MODI", API_KEY);
        call = apiInterface.getEverything(q, API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body() != null){
                    searchingLayout.setVisibility(View.GONE);
                    nestedScrollView5.setVisibility(View.VISIBLE);
                    if (!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticles();
                    adapter = new Adapter(articles, NewsActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    initListerner();

                }else {
                    Toast.makeText(NewsActivity.this,"No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(NewsActivity.this,"No Result", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

   /* private void initListerner(){
        adapter.setOnItemClickListerner(new Adapter.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Gson gson = new Gson();
                String vData = gson.toJson(articles);
                PrefUtils.storeArticles(getApplicationContext(), vData);
                Intent ii = new Intent(getApplicationContext(), NewsDetailsActivity.class);
                ii.putExtra("title","");
                ii.putExtra("position", position);

                startActivity(ii);
            }
        });
    }*/

   /* private void initialiseInfomo() {
        deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        infomoSDK1 = InfomoSDK.instance().init(getApplicationContext());
        infomoSDK1.setPublisherKey("17a75d7742bcc60b207c178e4eeeec4714b3c767");
       // infomoSDK1.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");
        // infomoSDK1.setPublisherKey("9bd09a0db59721aa0719f41894cf2fa697465884");
        //  infomoSDK1.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");
        infomoSDK1.setDomain(InfomoConstants.INFOMO_URL.INFOMO_INDONESIA);
        pref = SharedPreference.getInstance(NewsActivity.this);
        infomoSDK1.checkAppUpdateAndRegistration();
        infomoSDK1.setOperationCompleteCallback(this);


    }

    private void  askforPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 && apiKey != null) {
            ArrayList<String> allpermissions = new ArrayList<String>();
            allpermissions.add(android.Manifest.permission.CALL_PHONE);
            allpermissions.add(android.Manifest.permission.READ_PHONE_STATE);
            allpermissions.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            allpermissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            allpermissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
            allpermissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            allpermissions.add(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);

            ArrayList<String> requestpermissions = new ArrayList<String>();

            for (String permission : allpermissions) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) == PackageManager.PERMISSION_DENIED) {
                    requestpermissions.add(permission);
                }
            }
            if (requestpermissions.size() > 0) {
                ActivityCompat.requestPermissions(this, requestpermissions.toArray(new String[requestpermissions.size()]), 11);
            } else {
                boolean prefUpdated = pref.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
                if (!prefUpdated) {
                    pref.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                    CommonUtils.setUserAdSelection(pref, NewsActivity.this);
                }
            }
        } else {
            boolean prefUpdated = pref.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
            if (!prefUpdated) {
                pref.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                CommonUtils.setUserAdSelection(pref, NewsActivity.this);
            }
        }

        fcmToken = pref.getString(SharedPreference.FCM_SERVER_TOKEN, null);
        if (fcmToken == null) {
            FirebaseApp.initializeApp(NewsActivity.this);
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    fcmToken = instanceIdResult.getToken();
                    registerTheDevice();
                }
            });
            FirebaseInstanceId.getInstance().getInstanceId().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (fcmToken == null) {
                        fcmToken = "InvalidToken";
                    }
                }
            });


            //If it has failed this time to get the Firebase token, then it has to updated later
            //Once you get it by using
            //sdk.updateDeviceToken(fcmToken);

        }
    }

    private void registerTheDevice() {
        if (pref.getString(SharedPreference.KEY_APIKEY, null) == null) {
            deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    infomoSDK1.register(deviceID, fcmToken);
                    infomoSDK1.sync();
                    infomoSDK1.showAd();
                   // loadCategoryList(infomoSDK1);

                }
            }, 3000);



*//*


            if (infomoSDK != null)
                infomoSDK.showAd();*//*

        } else {
            infomoSDK1.sync();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    *//*
                    if (infomoSDK != null)
                        infomoSDK.showAd();*//*
                }

            }, 5000);




        }
        CommonUtils.enableDebugMode(NewsActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            SharedPreference adSettings = SharedPreference.getInstance(getApplicationContext());
            boolean prefUpdated = adSettings.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
            int counter = 0;
            if (!prefUpdated) {
                adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                CommonUtils.setUserAdSelection(adSettings, NewsActivity.this);
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            } else {
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                            CommonUtils.setUserAdSelection(adSettings, NewsActivity.this);

                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            }

            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED_TO_SERVER, false);
        }
    }*/


    @Override
    public void onOperationComplete(String s, Object o, int i) {
//        notifyOperationComplet= (NotifyOperationComplete) o;
    }


    @Override
    public void onBackPressed() {
       // finish();
        finishAffinity();

       /* hideSoftKeyboard();
        if (!searchView.isIconified()) {
            searchView.setIconified(true); //for remove searching
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }*/
    }


    private void hideSoftKeyboard(){
        View view = NewsActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) NewsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow (view.getWindowToken (), 0);
        }
    }

}
