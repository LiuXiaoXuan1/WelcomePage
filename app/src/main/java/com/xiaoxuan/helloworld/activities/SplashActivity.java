package com.xiaoxuan.helloworld.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.xiaoxuan.helloworld.R;


/**
 * Created by xiaoxuan on 2015/9/18.
 */
public class SplashActivity extends ActionBarActivity {
    private final int SPLASH_DISPLAY_LENGHT = 1000; //延迟三秒

    private int count;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        initPreference();
    }

    private void initActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, A.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }

    private void initPreference() {
        preferences = getSharedPreferences("guide", Context.MODE_PRIVATE);
        count = preferences.getInt("enterCount", 0);
        //判断是不是首次登录，
        if (count == 0) {
            Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            initActivity();
        }
        //测试应用运行次数
        Toast.makeText(this, "软件运行次数" + "  " + count, Toast.LENGTH_LONG).show();
        editor = preferences.edit();
        //存入数据
        editor.putInt("enterCount", ++count);
        //提交修改
        editor.commit();
    }
}
