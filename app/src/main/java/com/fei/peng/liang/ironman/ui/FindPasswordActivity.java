package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;

import java.util.List;

public class FindPasswordActivity extends Activity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
    }


    public void send(View view){
        String sms_content="您的账号为"+sharedPreferences.getString("username",null)+"您的密码为："+
                sharedPreferences.getString("password",null);
        String phone_number =sharedPreferences.getString("email",null);
        SmsManager smsManager = SmsManager.getDefault();
        if(sms_content.length() > 70) {
            List<String> contents = smsManager.divideMessage(sms_content);
            for(String sms : contents) {
                smsManager.sendTextMessage(phone_number, null, sms, null, null);
            }
        } else {
            smsManager.sendTextMessage(phone_number, null, sms_content, null, null);
        }
        Toast.makeText(this,"验证发送成功",Toast.LENGTH_LONG).show();
    }


    public void cancle(View view){
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.find_password, menu);
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
