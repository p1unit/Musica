package com.example.punee.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by punee on 10/6/2017.
 */

public class SplashscreenActivity extends AppCompatActivity{
    ImageView imageViewSplash;
    TextView txtAppName;
    RelativeLayout relativeLayout;
    Thread Splashthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewSplash =(ImageView) findViewById(R.id.imageViewsplash);
        txtAppName =(TextView) findViewById(R.id.txtAppName);
        relativeLayout=(RelativeLayout) findViewById(R.id.relative);
        startAmination();
    }

    private void startAmination(){

        Animation rotate= AnimationUtils.loadAnimation(this,R.anim.rotate);
        Animation translate= AnimationUtils.loadAnimation(this,R.anim.translate);
        rotate.reset();
        translate.reset();
        relativeLayout.clearAnimation();

        imageViewSplash.startAnimation(rotate);
      //  txtAppName.startAnimation(translate);

        Splashthread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited=0;
                while(waited<1800){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited +=200;
                }
                Intent intent = new Intent(SplashscreenActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        };
        Splashthread.start();
    }
}
