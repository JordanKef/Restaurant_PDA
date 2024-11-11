package com.example.restaurantpda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class OrderManager extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private String tableNumber;
    private List<Item> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        itemsList = new ArrayList<>();

        // Get table number from intent
        tableNumber = getIntent().getStringExtra("TABLE_NUMBER");

        // Setup RecyclerView
        recyclerView = findViewById(R.id.products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemsAdapter(itemsList);
        recyclerView.setAdapter(adapter);

        // Set table number in TextView
        TextView tableNumTextView = findViewById(R.id.table_num);
        tableNumTextView.setText("Τραπέζι: " + tableNumber);

        // Back button listener
        findViewById(R.id.button_back).setOnClickListener(v -> startActivity(new Intent(OrderManager.this, TableManager.class)));

        // Category buttons listeners
        setupCategoryButtonListeners();
    }

    private void setupCategoryButtonListeners() {
        findViewById(R.id.button_meats).setOnClickListener(v -> fetchItemsByCategory("Meats"));
        findViewById(R.id.button_salads).setOnClickListener(v -> fetchItemsByCategory("Salads"));
        findViewById(R.id.button_sides).setOnClickListener(v -> fetchItemsByCategory("Sides"));
        findViewById(R.id.button_drinks).setOnClickListener(v -> fetchItemsByCategory("Drinks"));
    }

    private void fetchItemsByCategory(String category) {
        // Reference to the table's subcollection
        CollectionReference tableItemsRef = db.collection("tables").document(tableNumber).collection(category);

        tableItemsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                itemsList.clear(); // Clear current list

                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Assuming each document in the subcollection has fields like "name" and "quantity"
                    String name = document.getString("name");
                    Long quantity = document.getLong("quantity");

                    // Add the item to the list
                    itemsList.add(new Item(name, quantity));
                }

                // Notify adapter of data change
                adapter.notifyDataSetChanged();
            } else {
                Log.d("OrderManager", "Error getting documents: ", task.getException());
            }
        });
    }
}
