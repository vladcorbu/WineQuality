package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterAnimation();
    }

    public void scale_animation(float scaleX, float scaleY, float traslation, int time, ImageView image){
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(image, "scaleX", scaleX);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(image, "scaleY", scaleY);
        scaleDownX.setDuration(time);
        scaleDownY.setDuration(time);
        ObjectAnimator moveUpY = ObjectAnimator.ofFloat(image, "translationY", traslation);
        moveUpY.setDuration(time);
        AnimatorSet scaleDown = new AnimatorSet();
        AnimatorSet moveUp = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        moveUp.play(moveUpY);
        scaleDown.start();
        moveUp.start();

    }

    public void enterAnimation(){
        final ImageView barman = (ImageView) findViewById(R.id.barman);
        final ImageView test_wine = (ImageView) findViewById(R.id.test_wine);
        final ImageView import_data = (ImageView) findViewById(R.id.import_data);
        final ImageView info = (ImageView) findViewById(R.id.info);
        scale_animation(0f, 0f, 0, 0,  test_wine);
        scale_animation(0f, 0f, 0, 0,  import_data);
        scale_animation(0f, 0f, 0, 0, info);
        new CountDownTimer(1500, 1000){
            @Override
            public void onFinish() {
                scale_animation(0f, 0f, 1000, 1100, barman);
                scale_animation(1.2f, 1.2f, 0, 1500, test_wine);
                scale_animation(1.2f, 1.2f, 0, 1500, import_data);
                scale_animation(1.2f, 1.2f, 0, 1500, info);

            }

            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }


    public void test_wine(View view) {
        Intent intent = new Intent(this, testWine.class);
        startActivity(intent);
    }

    public void appInfo(View view) {
        Intent intent = new Intent(this, infoActivity.class);
        startActivity(intent);
    }

    public void importData(View view) {
        Intent intent = new Intent(this, importData.class);
        startActivity(intent);
    }
}