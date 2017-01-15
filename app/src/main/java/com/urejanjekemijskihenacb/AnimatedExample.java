package com.urejanjekemijskihenacb;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AnimatedExample extends AppCompatActivity {

    VideoView animatedVideo;

    //boolean pause=false;
    boolean start=true;
    boolean playtillEnd;
    Spinner animationList;
    String selectedAnimation;
    TextView description;
    String uriPath2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_example);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        playtillEnd=false;

        List<String> list=new ArrayList<String>();
        list.add("Ammonia");
        list.add("Complete Combustion");
        list.add("Incomplete Combustion");


        ArrayAdapter<String> adapterSpinner=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list);
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        description=(TextView)this.findViewById(R.id.descriptionAnimation);
        animationList=(Spinner)this.findViewById(R.id.animationsSpinner);
        animationList.setAdapter(adapterSpinner);
        animatedVideo=(VideoView)this.findViewById(R.id.animatedVideo);

        animationList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedAnimation=adapterView.getItemAtPosition(i).toString();
                    if(selectedAnimation=="Ammonia"){
                        uriPath2 = "android.resource://com.urejanjekemijskihenacb/"+R.raw.nh3;
                        description.setText(R.string.Ammonia);
                    }
                    if(selectedAnimation=="Complete Combustion"){
                        uriPath2 = "android.resource://com.urejanjekemijskihenacb/"+R.raw.ch4_01;
                        description.setText(R.string.CompleteCombustion);
                    }
                    if(selectedAnimation=="Incomplete Combustion"){
                        uriPath2 = "android.resource://com.urejanjekemijskihenacb/"+R.raw.ch4;
                        description.setText(R.string.IncompleteCombustion);
                    }

                    Uri uri2 = Uri.parse(uriPath2);
                    animatedVideo.setVideoURI(uri2);
                    animatedVideo.requestFocus();
                    animatedVideo.start();
                    start=false;
                    playtillEnd=true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }
}
