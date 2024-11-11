package com.example.restaurantpda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class TableManager extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_area);

        // Αποστολη Αριθμου Τραπεζιου

        ImageButton table101 = findViewById(R.id.table101);
        table101.setOnClickListener(v -> openOrderManager("101"));

        ImageButton table102 = findViewById(R.id.table102);
        table102.setOnClickListener(v -> openOrderManager("102"));

        ImageButton table103 = findViewById(R.id.table103);
        table103.setOnClickListener(v -> openOrderManager("103"));

        ImageButton table1 = findViewById(R.id.table1);
        table1.setOnClickListener(v -> openOrderManager("1"));

        ImageButton table2 = findViewById(R.id.table2);
        table2.setOnClickListener(v -> openOrderManager("2"));

        ImageButton table3 = findViewById(R.id.table3);
        table3.setOnClickListener(v -> openOrderManager("3"));

        ImageButton table4 = findViewById(R.id.table4);
        table4.setOnClickListener(v -> openOrderManager("4"));

        ImageButton table5 = findViewById(R.id.table5);
        table5.setOnClickListener(v -> openOrderManager("5"));

        ImageButton table6 = findViewById(R.id.table6);
        table6.setOnClickListener(v -> openOrderManager("6"));

        ImageButton table201 = findViewById(R.id.table201);
        table201.setOnClickListener(v -> openOrderManager("201"));

        ImageButton table202 = findViewById(R.id.table202);
        table202.setOnClickListener(v -> openOrderManager("202"));

        ImageButton table203 = findViewById(R.id.table203);
        table203.setOnClickListener(v -> openOrderManager("203"));

        ImageButton table204 = findViewById(R.id.table204);
        table204.setOnClickListener(v -> openOrderManager("204"));
    }

    private void openOrderManager(String tableNumber) {
        Intent intent = new Intent(TableManager.this, OrderManager.class);
        intent.putExtra("TABLE_NUMBER", tableNumber);
        startActivity(intent);
    }
}
