package com.fei.peng.liang.ironman.ui;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private String strpwd,strname,strphone;
    Intent intent;
    static boolean everload;
    private static EditText password,email_or_phone;

    static SharedPreferences sharedPreferences;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在初始化的时候，进行处理
        sharedPreferences=getSharedPreferences("user",Context.MODE_PRIVATE);
        strname = sharedPreferences.getString("username",null);
        strpwd = sharedPreferences.getString("password",null);
        strphone = sharedPreferences.getString("email",null);
        //理解LayoutINflater的真正的意义：就仅仅是得到layout的id返回一个View对象，然后再使用setContentView来设置界面
        LayoutInflater inflater = LayoutInflater.from(this);

        everload=this.getIntent().getBooleanExtra("everload",false);
        if (everload){

            //setContentView(R.layout.activity_main_ever);和下面的代码是一样的
            View layout = inflater.inflate(R.layout.activity_main_ever, null);
            setContentView(layout);


//            TextView welcome_name= (TextView)layout.findViewById(R.id.tv_welcome_name);
//            welcome_name.setText("liang");
//            sharedPreferences=this.getSharedPreferences("user", Context.MODE_PRIVATE);
//            welcome_name.setText(sharedPreferences.getString("username","")+"");
        }else {
            //View layout = inflater.inflate(R.layout.activity_main_never, null);
            //setContentView(R.layout.activity_main_never);
            View layout = inflater.inflate(R.layout.activity_main_never, null);

            setContentView(layout);
//            username = (EditText)findViewById(R.id.et_load_email);
//            password = (EditText)findViewById(R.id.et_regist_username);

        }


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void regist(View view){
        intent = new Intent(MainActivity.this,RegistActivity.class);
        startActivity(intent);

    }


    public void signin(View view){
//        System.out.println(username.getText().toString());
//        System.out.println(password.getText().toString());
        Toast.makeText(this,"有一些小小的问题啦！现在可以解决了",Toast.LENGTH_LONG).show();
//        System.out.println(password.getText().toString());
//        System.out.println(email_or_phone.getText().toString());
//        System.out.println(strname+strpwd+strphone);
//        if (strpwd.equals(password.getText().toString())  && (strname.equals(email_or_phone.getText().toString()) || strphone.equals(email_or_phone.getText().toString())) ){
//            intent = new Intent(MainActivity.this,LoadSuccessActivity.class);
//            startActivity(intent);
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            editor.putBoolean("everload",true);
//            editor.commit();
//            Toast.makeText(this,"登录成功!!",Toast.LENGTH_LONG).show();
//        }else {
//            Toast.makeText(this,"您输入的用户名或密码错误！！",Toast.LENGTH_LONG).show();
//            password.setText("");
//        }
    }

    public void tv_forget_password(View view){
//        intent = new Intent(MainActivity.this,FindPasswordActivity.class);
//        startActivity(intent);
        Toast.makeText(this,"forget",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    /*
    Go to the activities
     */

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:

                mTitle = getString(R.string.title_user);
                break;
            case 2:
                if (everload){
                    mTitle = getString(R.string.title_application);
                    intent = new Intent(MainActivity.this,ApplicationActivity.class);
                    startActivity(intent);
                }else {
                    LayoutInflater inflater = LayoutInflater.from(this);
                    View layout = inflater.inflate(R.layout.activity_main_never, null);
                    setContentView(layout);
                }
                break;
            case 3:
                mTitle = getString(R.string.title_setting);
                intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            case 4:
                //返回到桌面上，相当于退出
                intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        //在这个方法中获取控件的引用，很重要的
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView;
            if(everload){
                rootView = inflater.inflate(R.layout.fragment_main_ever, container, false);
                TextView textView= (TextView) rootView.findViewById(R.id.tv_welcome_name);
                String username = sharedPreferences.getString("username",null).toString();
                textView.setText(username+"您好");
            }else {
                rootView = inflater.inflate(R.layout.fragment_main_never, container, false);
                email_or_phone = (EditText) rootView.findViewById(R.id.et_load_email_and_phone);
                password = (EditText) rootView.findViewById(R.id.et_regist_load_password);

            }
           return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(intent);
        }
        return false;
    }

}
