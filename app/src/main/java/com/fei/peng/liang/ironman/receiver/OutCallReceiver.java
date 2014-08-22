package com.fei.peng.liang.ironman.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fei.peng.liang.ironman.ui.LostProtectedActivity;

public class OutCallReceiver extends BroadcastReceiver {
    public OutCallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String outnumber=getResultData();
        String enterPhoneBakNumber="0713";
        if (enterPhoneBakNumber.equals(outnumber)){
            Intent outintent=new Intent(context, LostProtectedActivity.class);
            //在一个新的任务栈中
            outintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(outintent);
            //拦截此号码，不然其显示在记录中
            setResultData(null);
        }
    }
}
