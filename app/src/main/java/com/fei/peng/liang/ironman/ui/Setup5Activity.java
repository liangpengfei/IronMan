package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.receiver.MyAdmin;


public class Setup5Activity extends Activity{

    private CheckBox cb_isprotecting;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupguide5);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        cb_isprotecting =  (CheckBox) this.findViewById(R.id.cb_isprotecting);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        // 初始化checkbox的状态
        boolean isprotecting = sp.getBoolean("isprotecting", false);
        if(isprotecting){
            cb_isprotecting.setText("手机防盗保护中");
            cb_isprotecting.setChecked(true);
        }

        cb_isprotecting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cb_isprotecting.setText("手机防盗保护中");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isprotecting", true);
                    editor.commit();
                }else {
                    cb_isprotecting.setText("没有开启防盗保护");
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isprotecting", false);
                    editor.commit();
                }

            }
        });

    }

    public void pre5(View view){
        Intent intent3 = new Intent(this,Setup5Activity.class);
        //一定要把当前的activity从任务栈里面移除
        finish();
        startActivity(intent3);
        //设置activity切换时候的动画效果
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
    }

    public void next5(View view) {
        if (cb_isprotecting.isChecked()) {
            finish();
            finishSetup();
            Toast.makeText(getBaseContext(), "IRONMAN 正在保护您！！", Toast.LENGTH_LONG).show();
        } else {
            //弹出对话框
            AlertDialog.Builder builder = new Builder(Setup5Activity.this);
            builder.setTitle("温馨提示");
            builder.setMessage("IronMan极大的保护了您的手机安全，强烈建议您开启手机防盗！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    finishSetup();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }
    }


    private void finishSetup() {
        // 设置一个标示 用户已经完成过设置向导

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("issteupalready", true);
        editor.commit();

        //获得系统的超级管理员权限
        DevicePolicyManager manager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        ComponentName mAdminName = new ComponentName(this, MyAdmin.class);

        //如果没有获取到就使用Intent来让用户获取
        if (!manager.isAdminActive(mAdminName)) {
            Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setup5, menu);
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
