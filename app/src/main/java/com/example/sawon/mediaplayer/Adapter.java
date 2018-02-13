package com.example.sawon.mediaplayer;


import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moonc on 2/8/2018.
 */

public class Adapter extends BaseAdapter {
    Activity activity;
    ArrayList<String> arrayList;
    String songInfo;
    private static LayoutInflater inflater = null;
    MediaPlayer mediaPlayer;

    public Adapter(Activity mcontext,ArrayList<String > marrayList) {
        activity = mcontext;
        arrayList = marrayList;

        inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter,null);
        TextView title_ = (TextView)view.findViewById(R.id.textView);
        String title = arrayList.get(i);
        title_.setText(title);
        return view;
    }
}
