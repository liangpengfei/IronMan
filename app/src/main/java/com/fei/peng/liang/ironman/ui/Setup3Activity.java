package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.fei.peng.liang.ironman.R;

public class Setup3Activity extends Activity {

    private EditText et_safenumber;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupguide3);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        et_safenumber = (EditText) findViewById(R.id.et_setup3_number);
        String safenumber=sharedPreferences.getString("safenumber","");
        et_safenumber.setText(safenumber);
    }

    public void select_number(View view){
        Intent intent = new Intent(this,SelectContactActivity.class);
        //0是请求码
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null){
            String number=data.getStringExtra("number");
            String name=data.getStringExtra("name");
            et_safenumber.setText(name+" :"+number);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void pre3(View view){
        Intent intent= new Intent(this,Setup2Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }

    public void next3(View view){
        Intent intent=new Intent(this,Setup4Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.translate_in,R.anim.translate_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup3, menu);
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
}
