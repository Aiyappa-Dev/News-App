package com.aimright.admin.demoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.aimright.admin.demoapp.R;

/*import com.thedascapital.www.newsapp.R;*/

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent user=new  Intent(WelcomeScreen.this, UserProfile.class);
            startActivity(user);
            }
        });

        next.setOnTouchListener((view, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                next.setBackgroundColor(getResources().getColor(R.color.color_blue));
            } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                next.setBackgroundColor(getResources().getColor(R.color.konvers_incoming_call));
            }
            return false;
        });

    }
}
