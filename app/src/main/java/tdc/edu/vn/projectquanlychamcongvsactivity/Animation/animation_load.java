package tdc.edu.vn.projectquanlychamcongvsactivity.Animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.LoginActivity;
import tdc.edu.vn.projectquanlychamcongvsactivity.R;

public class animation_load extends AppCompatActivity {
    boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_load);
        ImageView cat = (ImageView) findViewById(R.id.imgCat);
        final AnimationDrawable runningCat = (AnimationDrawable) cat.getDrawable();
        runningCat.start();
        final long SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(animation_load.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);



    }
}
