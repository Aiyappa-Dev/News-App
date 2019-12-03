package com.aimright.admin.demoapp1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import com.adimoro.business.adimorosdk.apis.InfomoSDK;
import com.adimoro.business.adimorosdk.interfaces.NotifyOperationComplete;
import com.adimoro.business.adimorosdk.utils.CommonUtils;
import com.adimoro.business.adimorosdk.utils.InfomoConstants;
import com.adimoro.business.adimorosdk.utils.LogE;
import com.adimoro.business.adimorosdk.utils.SharedPreference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.aimright.admin.demoapp1.R;
/*import com.thedascapital.www.newsapp.R;*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.adimoro.business.adimorosdk.utils.InfomoConstants.PARAMS_REPO.apiKey;

public class SplashScreen extends AppCompatActivity implements NotifyOperationComplete {
    Boolean isFirstTime;
    private String deviceID;
   // private InfomoSDK infomoSDK11;
    private SharedPreference pref;
    private String fcmToken;
    private static InfomoSDK infomoSDK1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        initialiseInfomo();
        askforPermissions();

        /*SharedPreferences app_preferences = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


        SharedPreferences.Editor editor = app_preferences.edit();

        isFirstTime = app_preferences.getBoolean("isFirstTime", true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFirstTime) {
                    //implement your first time logic
                    editor.putBoolean("isFirstTime", false);
                    editor.apply();
                    Intent i=new Intent(SplashScreen.this, WelcomeScreen.class);
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(SplashScreen.this, NewsActivity.class);
                    startActivity(i);
                }


            }
        }, 3000);*/
    }


    private void initialiseInfomo() {
        deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        infomoSDK1 = InfomoSDK.instance().init(getApplicationContext());
        /**/
       // infomoSDK1.setPublisherKey("17a75d7742bcc60b207c178e4eeeec4714b3c767");
        infomoSDK1.setPublisherKey("254e16e0130696a8fd5575454a36cad3dd806135");
//        infomoSDK1.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");
       // infomoSDK1.setPublisherKey("25b870c18a48612fa7fdc6498d2d0bd978e8e3fd");
       // infomoSDK1.setPublisherKey("25b870c18a48612fa7fdc6498d2d0bd978e8e3fd");
      //  infomoSDK1.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");
        // infomoSDK1.setPublisherKey("9bd09a0db59721aa0719f41894cf2fa697465884");

        /*pid11*/
         // infomoSDK1.setPublisherKey("6785b4f504d91be46575549d8bbba502cfe77942");

        /*pid 8*/
         // infomoSDK1.setPublisherKey("946539b707c39dbe4f76d9f59e36e18e269a0a2e");
        infomoSDK1.setDomain(InfomoConstants.INFOMO_URL.INFOMO_INDONESIA);
        pref = SharedPreference.getInstance(SplashScreen.this);
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
                    CommonUtils.setUserAdSelection(pref, SplashScreen.this);
                }
            }
        }

        else {
            boolean prefUpdated = pref.getBoolean(SharedPreference.KEY_PREF_UPDATED, false);
            if (!prefUpdated) {
                pref.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                CommonUtils.setUserAdSelection(pref, SplashScreen.this);
            }
        }

        fcmToken = pref.getString(SharedPreference.FCM_SERVER_TOKEN, null);
        if (fcmToken == null) {
            FirebaseApp.initializeApp(SplashScreen.this);
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    fcmToken = instanceIdResult.getToken();
                    LogE.printMe("fcm token :"+fcmToken);
                   // registerTheDevice();
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
        registerTheDevice();

    }

    private void registerTheDevice() {
        if (pref.getString(SharedPreference.KEY_APIKEY, null) == null) {
            deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Handler handler = new Handler();
            handler.postDelayed(() -> {

                infomoSDK1.register(deviceID, fcmToken);
                infomoSDK1.sync();
                infomoSDK1.showAd();
                // loadCategoryList(infomoSDK1);

            }, 3000);



/*
            if (infomoSDK != null)
                infomoSDK.showAd();*/

        } else {
            infomoSDK1.sync();

        }
        CommonUtils.enableDebugMode(SplashScreen.this);
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
                CommonUtils.setUserAdSelection(adSettings, SplashScreen.this);
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            }
            else {
                if (permissions != null && permissions.length > 0) {
                    for (String permission : permissions) {
                        if (permission.equals(android.Manifest.permission.READ_PHONE_STATE) && grantResults[counter] == PackageManager.PERMISSION_GRANTED) {
                            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED, true);
                            CommonUtils.setUserAdSelection(adSettings, SplashScreen.this);

                            adSettings.setBoolean("phonePermissionUpdated", true);
                        }
                        counter++;
                    }
                }
            }

            adSettings.setBoolean(SharedPreference.KEY_PREF_UPDATED_TO_SERVER, false);
        }


        Map<String, Integer> perms = new HashMap<String, Integer>();
        // Initial

        perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, PackageManager.PERMISSION_GRANTED);

        for (int i = 0; i < permissions.length; i++)
            perms.put(permissions[i], grantResults[i]);
        // Check for ACCESS_FINE_LOCATION
        if (perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                perms.get(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) == PackageManager.PERMISSION_GRANTED)
        {
            // All Permissions Granted
            // Here start the activity

            SharedPreferences app_preferences = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


            SharedPreferences.Editor editor = app_preferences.edit();

            isFirstTime = app_preferences.getBoolean("isFirstTime", true);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (isFirstTime) {
                        //implement your first time logic
                        editor.putBoolean("isFirstTime", false);
                        editor.apply();
                        Intent i=new Intent(SplashScreen.this, WelcomeScreen.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(SplashScreen.this, NewsActivity.class);
                        startActivity(i);
                    }


                }
            }, 3000);

        } else
            {
            SharedPreferences app_preferences = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


            SharedPreferences.Editor editor = app_preferences.edit();

            isFirstTime = app_preferences.getBoolean("isFirstTime", true);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (isFirstTime) {
                        //implement your first time logic
                        editor.putBoolean("isFirstTime", false);
                        editor.apply();
                        Intent i=new Intent(SplashScreen.this, WelcomeScreen.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(SplashScreen.this, NewsActivity.class);
                        startActivity(i);
                    }


                }
            }, 3000);
        }

    }


    public static InfomoSDK getReference1()
    {
        return  infomoSDK1;
    }


    @Override
    public void onOperationComplete(String s, Object o, int i) {
        Log.e("reaching","reaching");

    }
}
