package com.example.fanff.baseadapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemBean> itemBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            itemBeanList.add(new ItemBean(R.mipmap.ic_launcher,
                    "标题" + i, "内容" + i));
        }

        // 数据源与适配器的绑定
        ListView listView = (ListView) findViewById(R.id.lview);
        listView.setAdapter(new MyBaseAdapter(this, itemBeanList));
    }
}
