package com.fei.peng.liang.ironman.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/*
开机的时候会自动启动
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG ="BootCompleteReceiver" ;

    public BootCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e(TAG, "手机重启了");
        SharedPreferences sharaedPreferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean protecting = sharaedPreferences.getBoolean("protecting",false);
        if (protecting){
            String safenumber = sharaedPreferences.getString("safenumber","");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String currentsim=telephonyManager.getSimSerialNumber();
            String savedsim = sharaedPreferences.getString("simserial","");
            if (!savedsim.equals(currentsim)){
                Log.e(TAG,"发送警报");
                SmsManager smsManager= SmsManager.getDefault();
                smsManager.sendTextMessage(safenumber,null,"sim card changed",null,null);
            }
        }
    }
}
