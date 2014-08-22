package com.fei.peng.liang.ironman.service;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.fei.peng.liang.ironman.domain.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/*
提供了一个很好的接口，用来返回联系人的信息，返回的是一个List的列表类
 */

public class ContactInfoService {
	private Context context;
	
	
	
	public ContactInfoService(Context context) {
		this.context = context;
	}



    /*
    获取List类的ContactInfo，还可以在这里面扩展，添加获取其他信息的功能
     */
	public List<ContactInfo> getContactInfos(){
		ContentResolver resolver = context.getContentResolver();
		//1.��ȡ��ϵ�˵�id
		//2.�����ϵ�˵�id ��ȡ��ϵ������
		//3.�����ϵ�˵�id ��ݵ�type ��ȡ����Ӧ�����(�绰,email);
		List<ContactInfo> infos = new ArrayList<ContactInfo>();
		ContactInfo info ;
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri datauri = Uri.parse("content://com.android.contacts/data");
		Cursor cursor = resolver.query(uri, null, null, null, null);
		while (cursor.moveToNext()) {
		  info = new ContactInfo();
		   String id =	cursor.getString(cursor.getColumnIndex("_id"));
		   String name =	cursor.getString(cursor.getColumnIndex("display_name"));
		   info.setName(name);
		   Cursor datacursor =  resolver.query(datauri, null, "raw_contact_id=?", new String[]{id}, null);
		   while (datacursor.moveToNext()) {

			//mimetype
			String type = datacursor.getString(datacursor.getColumnIndex("mimetype"));
			if("vnd.android.cursor.item/phone_v2".equals(type)){
				String number = datacursor.getString(datacursor.getColumnIndex("data1"));
				info.setPhone(number);
			}
		}
		   datacursor.close();
		   infos.add(info);
		   info = null;
		}
		cursor.close();
		return infos;
	}
}
