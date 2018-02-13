package com.example.sawon.mediaplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    Button audio,video;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        audio = (Button)findViewById(R.id.audio);
        video = (Button)findViewById(R.id.video);

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this,MainActivity.class);
                startActivity(intent);
            }
        });


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) Options.this.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);

                Toast.makeText(getApplicationContext(),"Under Process . . . ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
