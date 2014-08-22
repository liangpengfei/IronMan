package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;

public class Setup2Activity extends Activity{

    private SharedPreferences sharedPreferences;
    private CheckBox checkBox;
    private Button bt_bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupguide2);
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        checkBox = (CheckBox) findViewById(R.id.cb_bind);
        sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        bt_bind = (Button) findViewById(R.id.bt_bind);
    }

    public void next2(View view){
        Intent intent = new Intent(this,Setup3Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.translate_in,R.anim.translate_out);
    }

    /*
    绑定联系人安全号码
     */
    public void bind(View view){
        String simseral = sharedPreferences.getString("simserial","");
        if(TextUtils.isEmpty(simseral)){
            //bind the simserial with the phone
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("simserial",getSimSerial());
            editor.commit();

            //reset the checkbox
            checkBox.setText("已绑定");
            checkBox.setChecked(true);
            bt_bind.setText("解除绑定");
        }else {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            //reset the simserial binding as ""
            editor.putString("simserial","");
            editor.commit();
            checkBox.setText("暂未绑定");
            checkBox.setChecked(false);
            bt_bind.setText("添加绑定");
        }
    }

    public void pre2(View view){
        Intent intent1 = new Intent(this,Setup1Activity.class);
        startActivity(intent1);
        finish();
        overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup2, menu);
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

    /*
    Get the SimSerialNumber of the phone
     */

    private String getSimSerial() {
        TelephonyManager telephonyManager= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        return telephonyManager.getSimSerialNumber();
    }
}
