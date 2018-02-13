package com.example.sawon.mediaplayer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class list_activity extends AppCompatActivity {

    ListView listView;
    Adapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_activity);
        listView = (ListView) findViewById(R.id.songList);
        final Intent intent = getIntent();
        ArrayList<String> song_list = intent.getStringArrayListExtra("song_list");
        adapter = new Adapter(list_activity.this, song_list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(list_activity.this, MainActivity.class);
                intent1.putExtra("song", i);
                startActivity(intent1);
            }
        });


    }
}
