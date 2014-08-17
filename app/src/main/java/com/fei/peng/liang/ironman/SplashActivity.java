package com.fei.peng.liang.ironman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.fei.peng.liang.ironman.ui.MainActivity;


public class SplashActivity extends Activity {
    SharedPreferences sharedPref;
    RelativeLayout relativeLayout;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        relativeLayout= (RelativeLayout) findViewById(R.id.splash_rl_layout);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(4000);
        relativeLayout.startAnimation(alphaAnimation);
        intent=new Intent(SplashActivity.this, MainActivity.class);

        String user=sharedPref.getString("user",null);
        String password=sharedPref.getString("password",null);

        if (user!=null && password!=null){
            intent.putExtra("everload",false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(intent);
        this.finish();
        return super.onTouchEvent(event);
    }


}
