package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import com.fei.peng.liang.ironman.utils.Md5Encoder;

public class LostProtectedActivity extends Activity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    private AlertDialog dialog;
    EditText et_first_pwd,et_first_pwd_conform,et_normal_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
        if (isSetupPwd()){
            showNormalEntryDialog();
        }else {
            showFirstEntryDialog();
        }
    }

    private void showFirstEntryDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(this,R.layout.first_entry_dialog,null);
        et_first_pwd= (EditText) view.findViewById(R.id.et_first_dialog_pwd);
        et_first_pwd_conform= (EditText) view.findViewById(R.id.et_first_dialog_pwd_confirm);
        Button normal_ok= (Button) view.findViewById(R.id.bt_first_dialog_ok);
        Button normal_cancle= (Button) view.findViewById(R.id.bt_first_dialog_cancle);
        normal_ok.setOnClickListener(this);
        normal_cancle.setOnClickListener(this);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();
    }

    private void showNormalEntryDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(this,R.layout.normal_entry_dialog,null);
        et_normal_pwd= (EditText) view.findViewById(R.id.et_normal_dialog_pwd);
        Button ok= (Button) view.findViewById(R.id.bt_normal_dialog_ok);
        Button cancle= (Button) view.findViewById(R.id.bt_normal_dialog_cancle);
        ok.setOnClickListener(this);
        cancle.setOnClickListener(this);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();
    }

    private boolean isSetupPwd() {
        String savedPwd=sharedPreferences.getString("password","");
        if (TextUtils.isEmpty(savedPwd)){
            return false;
        }else {
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost_protected, menu);
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

    //未完成
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_first_dialog_ok:
                String pwd = et_first_pwd.getText().toString().trim();
                String pwd_confirm = et_first_pwd_conform.getText().toString().trim();
                if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_confirm)){
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG);
                    //提前返回
                    return;
                }
                if (pwd.equals(pwd_confirm)){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("enterpassword", Md5Encoder.encode(pwd));
                    editor.commit();
                    dialog.dismiss();
                    finish();
                }else {
                    Toast.makeText(this,"您两次输入的密码不同",Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.bt_first_dialog_cancle:
                dialog.cancel();
                finish();
                break;
            case R.id.bt_normal_dialog_ok:
                String userenterpassword = et_normal_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(userenterpassword)){
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                String savedpassword=sharedPreferences.getString("enterpassword","");
                if (savedpassword.equals(Md5Encoder.encode(userenterpassword))){
                    Toast.makeText(this,"密码正确",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    return;
                }else {
                    Toast.makeText(this,"密码不正确",Toast.LENGTH_LONG).show();
                    return;
                }
            case R.id.bt_normal_dialog_cancle:
                dialog.cancel();
                finish();
                break;
            default:
                break;
        }
    }
}
