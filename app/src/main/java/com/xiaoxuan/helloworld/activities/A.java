package com.xiaoxuan.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxuan.helloworld.R;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaoxuan on 2015/7/13.
 */
public class A extends ActionBarActivity {
    @InjectView(R.id.user_edit)
    EditText nameEdit;
    @InjectView(R.id.pwd_edit)
    EditText pwdEdit;
    @InjectView(R.id.sent_btn)
    Button sentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.inject(this);
        initActivity();
    }

    public void initActivity() {

        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name, user_pwd;
                user_name = nameEdit.getText().toString().trim();
                user_pwd = pwdEdit.getText().toString().trim();
                if (user_name.equals("user") && user_pwd.equals("123456")) {
                    Intent intent = new Intent();
                    intent.setClass(A.this, B.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name", user_name);
                    bundle.putString("user_pwd", user_pwd);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(A.this, "登录失败!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

