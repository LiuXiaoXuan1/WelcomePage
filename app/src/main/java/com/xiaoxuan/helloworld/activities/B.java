package com.xiaoxuan.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.widget.TextView;

import com.xiaoxuan.helloworld.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoxuan on 2015/7/13.
 */
public class B extends ActionBarActivity {
    @InjectView(R.id.show_text)
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ButterKnife.inject(this);
        getActivity();
    }

    public void getActivity() {
        Intent intent = this.getIntent();
        String user_name = intent.getExtras().getString("user_name");
        String user_pwd = intent.getExtras().getString("user_pwd");

        showText.setText("用户：" + user_name + "登录成功!" + "且密码为：" + user_pwd);
    }

}
