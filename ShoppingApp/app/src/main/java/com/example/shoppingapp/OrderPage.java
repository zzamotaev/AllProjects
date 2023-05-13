package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page);

        List<String> itemTitles = new ArrayList<>();

        ListView order_list = findViewById(R.id.order_list);

        itemTitles.clear();
        updateList(order_list, itemTitles);

        for(Integer i: Order.items_id){
            itemTitles.add(MainActivity.fullItemList.get(i - 1).getTitle());
        }

        updateList(order_list, itemTitles);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemTitles.clear();
                Order.items_id.clear();
                updateList(order_list, itemTitles);
                Toast.makeText(OrderPage.this, "Спасибо за заказ!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void updateList(ListView order_list, List<String> itemTitles) {
        System.out.println("!!!, " + itemTitles.toString());
        order_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemTitles));
    }

    public void openMainPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}