package com.fei.peng.liang.ironman.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.domain.ContactInfo;
import com.fei.peng.liang.ironman.service.ContactInfoService;

import java.util.List;

public class SelectContactActivity extends Activity {
	private ListView lv;
    //注意infos是在onCreate方法中得到的，所以创建的时候就会有了
	private List<ContactInfo> infos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_contact);

        //ContactInfoService就是一个ContactInfo这个实体类向外提供接口的Service，也是一个内容提供者
		ContactInfoService service = new ContactInfoService(this);
        //得到一个List对象，其中包括了所有的联系人的信息，获取到List是正确地放入到ListView对象的前提
		infos = service.getContactInfos();
		
		
		lv = (ListView) this.findViewById(R.id.lv_select_contact);
		//设置适配器
        lv.setAdapter(new SelectContactAdapter());
        //添加事件处理
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
                //得到position这个地方的item引用，其实是一个实体类
				String phone = infos.get(position).getPhone();
                String name = infos.get(position).getName();
				Intent intent  = new Intent();
				intent.putExtra("number", phone);
                intent.putExtra("name",name);
				//设置结果码
                setResult(0, intent);
				finish();
				
			}});
	}

    /*
    这个方法和上面的方法是相互呼应的，是比较标准的啦
     */
	private class SelectContactAdapter extends BaseAdapter{

		public int getCount() {
			return infos.size();
		}

		public Object getItem(int position) {
			return infos.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			//自己定义了一个View，没有使用XML，其实是在这个方法中完成了映射，并且是手动完成的，要
            //明白是什么意思
            LayoutInflater layoutInflater=LayoutInflater.from(SelectContactActivity.this);

            View layout=layoutInflater.inflate(R.layout.select_contact_item,null);

            ContactInfo info = infos.get(position);
			TextView tv_name = (TextView) layout.findViewById(R.id.tv_contact_item_name);
			tv_name.setText("  " + info.getName());
			TextView tv_phone = (TextView) layout.findViewById(R.id.tv_contact_item_phonenumber);
			tv_phone.setText("  "+ info.getPhone());

//            ContactInfo info = infos.get(position);
//            LinearLayout ll = new LinearLayout(SelectContactActivity.this);
//            ll.setOrientation(LinearLayout.VERTICAL);
//            TextView tv_name = new TextView(SelectContactActivity.this);
//            tv_name.setText("����"+ info.getName());
//            tv_name.setTextColor(Color.WHITE);
//            tv_name.setTextSize(22);
//            TextView tv_phone = new TextView(SelectContactActivity.this);
//            tv_phone.setText("��ϵ�绰"+ info.getPhone());
//            tv_phone.setTextColor(Color.YELLOW);
//            tv_phone.setTextSize(16);
//            ll.addView(tv_name);
//            ll.addView(tv_phone);
			
			return layout;
		}
		
	}
}
