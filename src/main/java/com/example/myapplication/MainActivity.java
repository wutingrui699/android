package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<HashMap<String,Object>> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.goods_list);
        ArrayList<HashMap<String,Object>> goodsList = new ArrayList<>();

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("img", R.mipmap.ic_launcher);
        map1.put("name","香辣鸡腿堡");
        map1.put("price","12.8元");
        goodsList.add(map1);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("img", R.mipmap.ic_launcher);
        map2.put("name","奶茶大杯");
        map2.put("price","9.9元");
        goodsList.add(map2);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("img", R.mipmap.ic_launcher);
        map3.put("name","炸鸡套餐");
        map3.put("price","25元");
        goodsList.add(map3);

        SimpleAdapter adapter = new SimpleAdapter(
                MainActivity.this,
                goodsList,
                R.layout.item_goods,
                new String[]{"img","name","price"},
                new int[]{R.id.iv_item_img,R.id.tv_item_name,R.id.tv_item_price}
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> data = (HashMap<String, Object>) goodsList.get(position);
                Intent intent = new Intent(MainActivity.this, GoodsActivity.class);
                intent.putExtra("name", data.get("name").toString());
                intent.putExtra("price", data.get("price").toString());
                intent.putExtra("img", (Integer) data.get("img"));
                startActivity(intent);
            }
        });
    }
}
