package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shoppingapp.adapter.CategoryAdapter;
import com.example.shoppingapp.adapter.ItemAdapter;
import com.example.shoppingapp.model.Category;
import com.example.shoppingapp.model.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView reset;

    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;

    RecyclerView itemsRecycler;
    @SuppressLint("StaticFieldLeak")
    static ItemAdapter itemAdapter;
    static List<Item> itemList = new ArrayList<>();
    static List<Item> fullItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Телевизоры"));
        categoryList.add(new Category(2,"Холодильник"));
        categoryList.add(new Category(3,"СмартФоны"));
        categoryList.add(new Category(4,"Прочее"));

        setCategoryRecycler(categoryList);

        itemList.clear();
        fullItemList.clear();

        setItemRecycler(itemList);

        itemList.add(new Item(1,"cap","Phone","1099 руб",getString(R.string.item_description),3));
        itemList.add(new Item(2,"gloves","Холодильник","799 руб",getString(R.string.item_description),2));
        itemList.add(new Item(3,"glasses","Телевизоры","2499 руб",getString(R.string.item_description),1));

        fullItemList.addAll(itemList);

        setItemRecycler(itemList);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                itemList.clear();
                itemList.addAll(fullItemList);
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    public void openOrderPage(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    private void setItemRecycler(List<Item> itemList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        itemsRecycler = findViewById(R.id.itemsRecycler);
        itemsRecycler.setLayoutManager(layoutManager);

        itemAdapter = new ItemAdapter(this, itemList);
        itemsRecycler.setAdapter(itemAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void showItemsByCategory(int category){
        List<Item>filterItems = new ArrayList<>();

        for(Item i:fullItemList){
            if(i.getCategory() == category){
                filterItems.add(i);
            }
        }
        itemList.clear();
        itemAdapter.notifyDataSetChanged();

        itemList.addAll(filterItems);

        itemAdapter.notifyDataSetChanged(); //обновляем значения
    }
}