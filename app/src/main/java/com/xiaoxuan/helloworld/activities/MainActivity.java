package com.xiaoxuan.helloworld.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxuan.helloworld.R;
import com.xiaoxuan.helloworld.entities.IP;
import com.xiaoxuan.helloworld.utils.IPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.sumbit_button)
    Button sumbitBtn;
    @InjectView(R.id.editext1)
    EditText editText1;
    @InjectView(R.id.editext2)
    EditText editText2;
    @InjectView(R.id.editext3)
    EditText editText3;
    @InjectView(R.id.editext4)
    EditText editText4;
    @InjectView(R.id.editext5)
    EditText editText5;
    @InjectView(R.id.editext6)
    EditText editText6;
    @InjectView(R.id.editext7)
    EditText editText7;
    @InjectView(R.id.topic_btn)
    Button topicBtn;

    Map<String, String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        map.put("u_id","0");
        map.put("action","1");
        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPUtils.taoBaoIpService.getIp("202.202.32.202", new Callback<IP>() {
                    @Override
                    public void success(IP ip, Response response) {
                        editText1.setText(String.valueOf(ip.getCode()));
                        editText2.setText(ip.getData().getIp());
                        editText3.setText(ip.getData().getCountry());
                        editText4.setText(ip.getData().getArea());
                        editText5.setText(ip.getData().getRegion());
                        editText6.setText(ip.getData().getCity());
                        editText7.setText(ip.getData().getIsp());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(MainActivity.this, "查找失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
//       topicBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TopicUtils.urlApi.getData(Api.TOPIC_PATH, map, new Callback<Object>() {
//                    @Override
//                    public void success(Object o, Response response) {
//                        Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Toast.makeText(MainActivity.this, "获取动态失败", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });

        topicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, A.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
