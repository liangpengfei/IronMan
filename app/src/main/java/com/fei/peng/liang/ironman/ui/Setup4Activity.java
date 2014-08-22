package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;

public class Setup4Activity extends Activity {

    private EditText pwd,pwdcof;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupguide4);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        pwd = (EditText) this.findViewById(R.id.et_setup5_pwd);
        pwdcof = (EditText) this.findViewById(R.id.et_setup5_pwdcof);
    }

    public void pre4(View view){
        Intent intent2 = new Intent(this,Setup3Activity.class);
        //一定要把当前的activity从任务栈里面移除
        finish();
        startActivity(intent2);
        //设置activity切换时候的动画效果
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }

    public void next4(View view){
        //使用trim方法去掉字符串中的空格
        String spwd = pwd.getText().toString().trim();
        String spwdcof = pwdcof.getText().toString().trim();
        if(TextUtils.isEmpty(spwd)){
            Toast.makeText(this, "安全号码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!spwd.equals(spwdcof)){
            Toast.makeText(this, "两次安全号码不一致！", Toast.LENGTH_LONG).show();
            pwd.setText("");
            pwdcof.setText("");
            return;
        }else{
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("safepwd", spwd);
            editor.commit();
        }
        Intent intent4 = new Intent(this,Setup5Activity.class);
        //一定要把当前的activity从任务栈里面移除
        finish();
        startActivity(intent4);
        //设置activity切换时候的动画效果
        overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup4, menu);
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
