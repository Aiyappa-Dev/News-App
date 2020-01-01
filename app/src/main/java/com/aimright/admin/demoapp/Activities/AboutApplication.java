package com.aimright.admin.demoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aimright.admin.demoapp.R;

public class AboutApplication extends AppCompatActivity {
    TextView appVersion, sdkVersion, adsEnable, publisherName, stageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);
        appVersion=findViewById(R.id.app_version);
        sdkVersion=findViewById(R.id.sdk_version);
        adsEnable=findViewById(R.id.ads_enable);
        publisherName=findViewById(R.id.publisher_name);
        stageName=findViewById(R.id.stage_name);

        appVersion.setText("1.1");
        sdkVersion.setText("2.0");
        adsEnable.setText("End of call, 5th swipe of article & Banner Ads ");
        publisherName.setText("TestAccount1");
        stageName.setText("Staging");

    }
}
