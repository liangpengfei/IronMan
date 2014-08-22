package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.utils.Md5Encoder;

public class LostProtectedActivity extends Activity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private AlertDialog dialog;
    private EditText et_first_pwd,et_first_pwd_conform,et_normal_pwd;
    private TextView tv_lost_protected_number;
    private TextView tv_reentry_setup_guide;
    private CheckBox cb_isprotecting,cb,normal_cb;

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
        Button first_ok= (Button) view.findViewById(R.id.bt_first_dialog_ok);
        Button first_cancle= (Button) view.findViewById(R.id.bt_first_dialog_cancle);
        first_ok.setOnClickListener(this);
        first_cancle.setOnClickListener(this);
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
        et_normal_pwd.setText("");
        ok.setOnClickListener(this);
        cancle.setOnClickListener(this);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();
    }

    private boolean isSetupPwd() {
        String savedPwd=sharedPreferences.getString("enterpassword","");
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
                    et_first_pwd_conform.setText("");
                    et_first_pwd.setText("");
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

                    if(isSteup()){
                        //为这一个Activity设置View
                        setContentView(R.layout.lost_protected);
                        tv_lost_protected_number = (TextView) this.findViewById(R.id.tv_lost_protected_number);
                        tv_reentry_setup_guide = (TextView )this.findViewById(R.id.tv_reentry_setup_guide);
                        cb_isprotecting = (CheckBox )this.findViewById(R.id.cb_isprotecting);
                        // 初始化这些控件
                        String number = sharedPreferences.getString("safenumber", "");
                        tv_lost_protected_number.setText("安全手机号码为:"+number);
                        //重新进入设置向导的点击事件
                        tv_reentry_setup_guide.setOnClickListener(this);
                        // 初始化checkbox的状态
                        boolean isprotecting = sharedPreferences.getBoolean("isprotecting", false);
                        if(isprotecting){
                            cb_isprotecting.setText("手机防盗保护中");
                            cb_isprotecting.setChecked(true);
                        }

                        cb_isprotecting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    cb_isprotecting.setText("手机防盗保护中");
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("isprotecting", true);
                                    editor.commit();
                                }else {
                                    cb_isprotecting.setText("没有开启防盗保护");
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("isprotecting", false);
                                    editor.commit();
                                }

                            }
                        });
                    }else {
                        dialog.dismiss();
                        finish();
                        Intent intent = new Intent(LostProtectedActivity.this,Setup1Activity.class);
                        startActivity(intent);
                    }
                    return;
                }else {
                    Toast.makeText(this,"密码不正确",Toast.LENGTH_LONG).show();
                    et_normal_pwd.setText("");
                    return;
                }
            case R.id.bt_normal_dialog_cancle:
                dialog.cancel();
                finish();
                break;
            case R.id.tv_reentry_setup_guide:
                Intent intent = new Intent(LostProtectedActivity.this,Setup1Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /*
    监听Back按键事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            dialog.dismiss();
            finish();
        }
        return false;
    }

    private boolean isSteup(){
        return  sharedPreferences.getBoolean("issteupalready", false);
    }
}
