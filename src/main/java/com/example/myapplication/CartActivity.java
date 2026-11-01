package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    private ListView cartLv;
    private TextView emptyTip;
    private Button btnClear, btnPay;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLv = findViewById(R.id.cart_list);
        emptyTip = findViewById(R.id.empty_tip);
        btnClear = findViewById(R.id.btn_clear);
        btnPay = findViewById(R.id.btn_pay);

        refreshCart();

        btnClear.setOnClickListener(v -> {
            MainActivity.cartList.clear();
            refreshCart();
            Toast.makeText(CartActivity.this, "购物车已清空", Toast.LENGTH_SHORT).show();
        });

        btnPay.setOnClickListener(v -> {
            if(MainActivity.cartList.isEmpty()){
                Toast.makeText(CartActivity.this, "购物车为空，不能结算", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(CartActivity.this, "下单成功！仿美团订单提交完成", Toast.LENGTH_LONG).show();
                MainActivity.cartList.clear();
                refreshCart();
            }
        });

        cartLv.setOnItemLongClickListener((parent, view, position, id) -> {
            MainActivity.cartList.remove(position);
            refreshCart();
            Toast.makeText(CartActivity.this, "已删除该商品", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
    private void refreshCart(){
        if(MainActivity.cartList.isEmpty()){
            emptyTip.setText("购物车空空如也，快去选购商品吧！");
            cartLv.setVisibility(View.GONE);
            btnClear.setVisibility(View.GONE);
            btnPay.setVisibility(View.GONE);
        }else{
            emptyTip.setText("");
            cartLv.setVisibility(View.VISIBLE);
            btnClear.setVisibility(View.VISIBLE);
            btnPay.setVisibility(View.VISIBLE);

            adapter = new SimpleAdapter(
                    CartActivity.this,
                    MainActivity.cartList,
                    R.layout.item_goods,
                    new String[]{"name","price"},
                    new int[]{R.id.tv_item_name,R.id.tv_item_price}
            );
            cartLv.setAdapter(adapter);
        }
    }
}