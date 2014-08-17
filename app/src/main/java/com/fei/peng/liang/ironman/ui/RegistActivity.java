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

public class RegistActivity extends Activity {

    SharedPreferences sharedPreferences;
    EditText username,password,password2,email;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //set the navationBar
        getActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences=getSharedPreferences("user",Context.MODE_PRIVATE);

        username = (EditText) findViewById(R.id.et_regist_username);
        password = (EditText) findViewById(R.id.et_regist_password);
        password2 = (EditText) findViewById(R.id.et_regist_password2);
        email = (EditText) findViewById(R.id.et_regist_email);
    }

    public  void regist(View view){
        if (password.getText().toString().equals( password2.getText().toString())){
            //to do regist,save into the sharedPreferences
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",username.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.commit();
            intent=new Intent(RegistActivity.this,MainActivity.class);
            intent.putExtra("username",username.getText().toString());
            startActivity(intent);
        }
        else {
            //reset the password
            password.setText("");
            password2.setText("");
            Toast.makeText(this,"您两次输入的密码不一致！！",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.regist, menu);
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
