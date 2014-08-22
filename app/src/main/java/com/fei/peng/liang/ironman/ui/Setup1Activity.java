package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.utils.GestureListener;

public class Setup1Activity extends Activity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupguide1);
        linearLayout = (LinearLayout) findViewById(R.id.ll_setupguidel);
        //setLongClickable是必须的
        linearLayout.setLongClickable(true);
        linearLayout.setOnTouchListener(new MyGestureListener(this));
    }

    public void next1(View view){
        Intent intent =new Intent(this,Setup2Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup1, menu);
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

    /**
     * 继承GestureListener，重写left和right方法
     */
    private class MyGestureListener extends GestureListener {
        public MyGestureListener(Context context) {
            super(context);
        }

        @Override
        public boolean left() {
            Log.e("test", "向左滑");
            return super.left();
        }

        @Override
        public boolean right() {
            Log.e("test", "向右滑");
            return super.right();
        }
    }
}
