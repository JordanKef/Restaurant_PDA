package com.example.restaurantpda;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderManager extends AppCompatActivity {

    // [Αλλαγή] Αντικαταστάθηκε το Firebase με SQLite
    private SQLiteDatabase database;  // Αναφορά στη βάση δεδομένων SQLite

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private String tableNumber;
    private List<MenuItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering);

        // [Αλλαγή] Αρχικοποίηση SQLite database
        SqlFinder dbHelper = new SqlFinder(this); // Custom SQLiteOpenHelper κλάση
        database = dbHelper.getWritableDatabase();

        itemsList = new ArrayList<>();
        tableNumber = getIntent().getStringExtra("TABLE_NUMBER");

        recyclerView = findViewById(R.id.products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(itemsList);
        recyclerView.setAdapter(adapter);

        TextView tableNumTextView = findViewById(R.id.table_num);
        tableNumTextView.setText("Τραπέζι: " + tableNumber);

        findViewById(R.id.button_back).setOnClickListener(v -> {
            Intent intent = new Intent(OrderManager.this, TableManager.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(v -> clearOrder());
        findViewById(R.id.button_payment).setOnClickListener(v -> resetItems());
        findViewById(R.id.order_button).setOnClickListener(v -> submitOrder());

        setupCategoryButtonListeners();
    }

    private void setupCategoryButtonListeners() {
        findViewById(R.id.button_meats).setOnClickListener(v -> fetchItemsByCategory("Meats"));
        findViewById(R.id.button_salads).setOnClickListener(v -> fetchItemsByCategory("Salads"));
        findViewById(R.id.button_sides).setOnClickListener(v -> fetchItemsByCategory("Sides"));
        findViewById(R.id.button_drinks).setOnClickListener(v -> fetchItemsByCategory("Drinks"));
    }

    // [Αλλαγή] Λήψη δεδομένων από SQLite βάση δεδομένων
    private void fetchItemsByCategory(String category) {
        itemsList.clear(); // Καθαρίζει τη λίστα πριν προσθέσει νέα δεδομένα
        String query = "SELECT name, quantity FROM items WHERE tableNumber = ? AND category = ?";
        Cursor cursor = database.rawQuery(query, new String[]{tableNumber, category});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                int quantity = cursor.getInt(1);
                itemsList.add(new MenuItem(name, quantity));
            }
            cursor.close();
        }

        adapter.notifyDataSetChanged();
    }

    // [Αλλαγή] Διαγραφή παραγγελίας από τη βάση δεδομένων
    private void clearOrder() {
        database.delete("items", "tableNumber = ?", new String[]{tableNumber});
        itemsList.clear();
        adapter.notifyDataSetChanged();
    }

    // [Αλλαγή] Επαναφορά προϊόντων στη βάση δεδομένων
    private void resetItems() {
        ContentValues values = new ContentValues();
        values.put("quantity", 0);
        values.put("comment", "");
        database.update("items", values, "tableNumber = ?", new String[]{tableNumber});
    }

    private void submitOrder() {
        Log.d("OrderManager", "Η παραγγελία υποβλήθηκε για το τραπέζι " + tableNumber);
        // Εδώ μπορείς να προσθέσεις custom λογική υποβολής
    }
}
