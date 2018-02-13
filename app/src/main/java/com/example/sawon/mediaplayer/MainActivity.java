package com.example.sawon.mediaplayer;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public  static  int i = 0;
    public static MediaPlayer mediaPlayer;
    public static int clicked = 0;
    public static int songPosition = 0;
    Button left, right,pause,list_btn_,volume_up,volume_down;
    TextView songName;
    Handler handler;
    ArrayList<String> arrayList;
    ConstraintLayout constraintLayout;
    AudioManager audioManager;
    int[] images = {R.drawable.b4,R.drawable.b3,R.drawable.b2,R.drawable.b3};

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        constraintLayout = (ConstraintLayout)findViewById(R.id.back);
        arrayList = new ArrayList<>();
        songName = (TextView)findViewById(R.id.textView4);
        right = (Button)findViewById(R.id.right);
        left = (Button)findViewById(R.id.left);
        pause = (Button)findViewById(R.id.pause);
        list_btn_ = (Button)findViewById(R.id.list_btn);
        volume_up = (Button)findViewById(R.id.volumn_up);
        volume_down = (Button)findViewById(R.id.volumn_down);
        audioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
        final File music_dic = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music");
        final File[] listFiles = music_dic.listFiles();



        for(int i = 0;i<listFiles.length;i++)
        {
            arrayList.add(listFiles[i].getName());
        }


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            constraintLayout.setBackgroundResource(images[i]);
                        }
                    });

                    if (i == 3)
                        i = 0;
                    else
                        i++;

                    SystemClock.sleep(12000);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();



        volume_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_PLAY_SOUND);
            }
        });

        volume_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND);
            }
        });


        int song;
        Intent intent = getIntent();
        if(intent.getIntExtra("song",-1)>=0){
            song = intent.getIntExtra("song",0);
            clicked = 0;
            songPosition = song;
            songName.setText(arrayList.get(songPosition));
            pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);
            try {
                stopMusic();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.parse(listFiles[song].getAbsolutePath());
            mediaPlayer = MediaPlayer.create(MainActivity.this,uri);
            mediaPlayer.start();


            // Toast.makeText(getApplicationContext(),listFiles[song].getAbsolutePath().toString(),Toast.LENGTH_SHORT).show();
        }


        list_btn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,list_activity.class);
                intent.putStringArrayListExtra("song_list",arrayList);
                startActivity(intent);
            }
        });





        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                songName.setText(arrayList.get(songPosition));
                pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);

                if(clicked%2==0) {
                    if (mediaPlayer != null)
                        mediaPlayer.pause();
                    pause.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
                }
                else {
                    if (mediaPlayer != null){
                        mediaPlayer.start();
                    }else
                    {
                        Uri uri = Uri.parse(listFiles[songPosition].getAbsolutePath());
                        mediaPlayer = MediaPlayer.create(MainActivity.this,uri);
                        mediaPlayer.start();
                        // Toast.makeText(getApplicationContext(),listFiles[songPosition].getAbsolutePath().toString(),Toast.LENGTH_SHORT).show();
                    }

                    pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);

                }

                clicked++;
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clicked = 0;
                pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);


                songPosition = songPosition + 1;
                if(songPosition>=listFiles.length)
                    songPosition = listFiles.length - 1;

                songName.setText(arrayList.get(songPosition));

                try {
                    stopMusic();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.parse(listFiles[songPosition].getAbsolutePath());
                mediaPlayer = MediaPlayer.create(MainActivity.this,uri);
                mediaPlayer.start();


                //  Toast.makeText(getApplicationContext(),listFiles[songPosition].getAbsolutePath().toString(),Toast.LENGTH_SHORT).show();
            }
        });


//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                clicked = 0;
//                pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);
//
//
//                songPosition = songPosition + 1;
//                if(songPosition>=listFiles.length)
//                    songPosition = listFiles.length - 1;
//
//                songName.setText(arrayList.get(songPosition));
//
//                try {
//                    stopMusic();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Uri uri = Uri.parse(listFiles[songPosition].getAbsolutePath());
//                mediaPlayer = MediaPlayer.create(MainActivity.this,uri);
//                mediaPlayer.start();
//
//
//                Toast.makeText(getApplicationContext(),listFiles[songPosition].getAbsolutePath().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clicked = 0;
                pause.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_24dp);


                songPosition = songPosition - 1;
                if(songPosition<0)
                    songPosition = 0;

                songName.setText(arrayList.get(songPosition));

                try {
                    stopMusic();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.parse(listFiles[songPosition].getAbsolutePath());
                mediaPlayer = MediaPlayer.create(MainActivity.this,uri);
                mediaPlayer.start();


                //   Toast.makeText(getApplicationContext(),listFiles[songPosition].getAbsolutePath().toString(),Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }

    private void stopMusic() throws IOException {

        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }

    }
}
