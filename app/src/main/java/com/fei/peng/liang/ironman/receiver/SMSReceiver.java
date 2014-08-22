package com.fei.peng.liang.ironman.receiver;


import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;


import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.service.GPSInfoProvider;

/*
仅仅只是一个监听器，前提是激活了系统管理员权限
 */

public class SMSReceiver extends BroadcastReceiver {
	private SharedPreferences sp;

	private static final String TAG = "SMSReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        //获取安全密码
        String pwd =sp.getString("safepwd", "");

        //关键是怎么得到拦截到的信息
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for (Object pdu : pdus) {
            //得到信息整体
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
		    //得到信息主体
			String content = sms.getMessageBody();
			//得到联系人号码
            String sender = sms.getOriginatingAddress();

			if (("#*location*#"+pwd).equals(content)) {
				//终止广播�㲥
				abortBroadcast(); 
				GPSInfoProvider provider = GPSInfoProvider.getInstance(context);

				String location = provider.getLocation();
				//得到信息发送器
                SmsManager smsmanager = SmsManager.getDefault();
				if ("".equals(location)) {
                    //什么也不做
				} else {
					smsmanager.sendTextMessage(sender, null, location, null,null);
				}
			}else if(("#*locknow*#"+pwd).equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.resetPassword(pwd, 0);
				manager.lockNow();
				abortBroadcast();
			}else if(("#*delete*#"+pwd).equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.wipeData(0);
				abortBroadcast();
			}else if(("#*alarm*#"+pwd).equals(content)){
				MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
				player.setVolume(1.0f, 1.0f);
				player.start();
				abortBroadcast();
			}
		}

	}
}
