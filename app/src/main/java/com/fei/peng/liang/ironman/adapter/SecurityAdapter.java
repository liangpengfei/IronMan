package com.fei.peng.liang.ironman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.ui.SecurityFragment;

/**
 * Created by Administrator on 14-8-19.
 */
public class SecurityAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private static final int [] icons={R.drawable.shadu,R.drawable.fangdao};
    private static final String [] names={"手机杀毒","手机防盗"};
    public SecurityAdapter(Context context){
        this.context=context;
        //在构造方法中进行初始化
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.security_item,null);
        TextView tv_name= (TextView) view.findViewById(R.id.tv_security_item_name);
        ImageView iv_icon= (ImageView) view.findViewById(R.id.iv_security_item_icon);
        tv_name.setText(names[position]);
        iv_icon.setImageResource(icons[position]);
        return view;
    }
}
