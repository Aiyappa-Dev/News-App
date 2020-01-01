package com.aimright.admin.demoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.aimright.admin.demoapp.R;

public class Youtube extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        WebView youtubeWV=findViewById(R.id.youtubeWV);
        WebSettings webSettings = youtubeWV.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        youtubeWV.getSettings().setMediaPlaybackRequiresUserGesture(false);
      //  youtubeWV.setWebChromeClient(); = WebChromeClient()
        youtubeWV.loadUrl("https://www.youtube.com/watch?v=jL8uDJJBjMA");
    }
}
