package com.aimright.admin.demoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.aimright.admin.demoapp.Adapters.AdItemArrayAdapter;
import com.aimright.admin.demoapp.Models.AdItems;
import com.aimright.admin.demoapp.R;
/*import com.thedascapital.www.newsapp.R;*/

import java.util.ArrayList;

public class AdPreferences extends AppCompatActivity {

    private RecyclerView recyclerView;
  //  private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_preferences);
       // toolbar = findViewById(R.id.toolBar);
      //  setSupportActionBar(toolbar);
      /*  getSupportActionBar().setTitle("Ad Preferences");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        // Initializing list view with the custom adapter
        ArrayList <AdItems> itemList = new ArrayList<AdItems>();

        AdItemArrayAdapter itemArrayAdapter = new AdItemArrayAdapter(R.layout.list_item, itemList);
        recyclerView = (RecyclerView) findViewById(R.id.add_preference);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemArrayAdapter);

        // Populating list items
        itemList.add(new AdItems("Food"));
        itemList.add(new AdItems("Health"));
        itemList.add(new AdItems("Sports"));
        itemList.add(new AdItems("Travel"));
       /* for(int i=0; i<100; i++) {
            itemList.add(new AdItems("Item " + i));
        }*/

        Button done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdPreferences.this, NewsActivity.class);
                startActivity(i);
                finish();
            }
        });

        done.setOnTouchListener((view, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                done.setBackgroundColor(getResources().getColor(R.color.color_blue));
            } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                done.setBackgroundColor(getResources().getColor(R.color.konvers_incoming_call));
            }
            return false;
        });
    }
   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }*/

}
