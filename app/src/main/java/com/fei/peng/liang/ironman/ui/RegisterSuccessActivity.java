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
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;

public class RegisterSuccessActivity extends Activity {

    String username,password;
    EditText etusername,etpassword;
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        sharedPreferences=this.getSharedPreferences("user",Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username",null);
        password = sharedPreferences.getString("password",null);

        etusername = (EditText) findViewById(R.id.et_regist_load_username);
        etpassword = (EditText) findViewById(R.id.et_regist_load_password);

        etusername.setText(getIntent().getStringExtra("username"));


    }

    public void sign_in(View view){

        System.out.println(username);
        System.out.println(password);
        System.out.println(etusername.getText().toString());
        System.out.println(etpassword.getText().toString());

        if (password.equals(etpassword.getText().toString())  && username.equals(etusername.getText().toString()) ){
            intent = new Intent(RegisterSuccessActivity.this,LoadSuccessActivity.class);
            startActivity(intent);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("everload",true);
            editor.commit();
            Toast.makeText(this,"登录成功!!",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"您输入的用户名或密码错误！！",Toast.LENGTH_LONG).show();
            etpassword.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_success, menu);
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
