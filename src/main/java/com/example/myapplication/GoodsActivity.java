package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class GoodsActivity extends AppCompatActivity {

    private TextView tvGoodName,tvGoodPrice;
    private ImageView ivGoodImg;
    private Button btnAddCart,btnToCart;

    private String goodsName;
    private String goodsPrice;
    private int goodsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        tvGoodName = findViewById(R.id.tv_good_name);
        tvGoodPrice = findViewById(R.id.tv_good_price);
        ivGoodImg = findViewById(R.id.iv_good_img);
        btnAddCart = findViewById(R.id.btn_add_cart);
        btnToCart = findViewById(R.id.btn_to_cart);

        Intent intent = getIntent();
        goodsName = intent.getStringExtra("name");
        goodsPrice = intent.getStringExtra("price");
        goodsImg = intent.getIntExtra("img",0);

        tvGoodName.setText(goodsName);
        tvGoodPrice.setText(goodsPrice);
        ivGoodImg.setImageResource(goodsImg);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("name", goodsName);
                map.put("price", goodsPrice);
                map.put("img", goodsImg);
                MainActivity.cartList.add(map);
                Toast.makeText(GoodsActivity.this,"商品已加入购物车！",Toast.LENGTH_SHORT).show();
            }
        });

        btnToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCart = new Intent(GoodsActivity.this, CartActivity.class);
                startActivity(intentCart);
            }
        });
    }
}
