package com.urejanjekemijskihenacb;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class AnimatedExample extends AppCompatActivity {

    VideoView animatedVideo;

    //boolean pause=false;
    boolean start=true;
    boolean playtillEnd;
    Button play;
    Button pause;
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_example);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        playtillEnd=false;


        play=(Button)this.findViewById(R.id.playAnimatedVideo);
        pause=(Button)this.findViewById(R.id.pauseAnimatedVideo);
        stop=(Button)this.findViewById(R.id.stopAnimatedVideo);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(playtillEnd==true){
                    animatedVideo.resume();
                    //playtillEnd=false;
                }
                else
                {
                    Play();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatedVideo.pause();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatedVideo.stopPlayback();
                start=true;
            }
        });
        /*
        }*/




    }
    public void Play(){


        if(start==true){
            animatedVideo=(VideoView)this.findViewById(R.id.animatedVideo);
            String uriPath2 = "android.resource://com.urejanjekemijskihenacb/"+R.raw.nh3;
            Uri uri2 = Uri.parse(uriPath2);
            animatedVideo.setVideoURI(uri2);
            animatedVideo.requestFocus();
            animatedVideo.start();
            start=false;
            playtillEnd=true;
        }else {
            animatedVideo.resume();

        }

    }
}
