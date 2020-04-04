package com.eleven.labs.waterqualitydashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout myImageView= (LinearLayout) findViewById(R.id.layout1);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        myImageView.startAnimation(myFadeInAnimation); //Set animation to your ImageView

        myImageView= (LinearLayout) findViewById(R.id.layout2);
        myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        myImageView.startAnimation(myFadeInAnimation); //Set animation to your ImageView

        new CountDownTimer(1000*1,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
