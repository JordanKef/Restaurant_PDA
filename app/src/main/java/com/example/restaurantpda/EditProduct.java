package com.example.restaurantpda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class EditProduct extends AppCompatActivity {

    private String tableNumber;
    private String itemName;

    private TextView productNameTextView;
    private EditText quantityEditText;
    private EditText commentsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product); // Ενημερώθηκε το layout σε product.xml

        // Λήψη δεδομένων από το Intent
        Intent intent = getIntent();
        tableNumber = intent.getStringExtra("TABLE_NUMBER");
        itemName = intent.getStringExtra("ITEM_NAME");
        int quantity = intent.getIntExtra("QUANTITY", 0);
        String comments = intent.getStringExtra("COMMENTS");

        // Αρχικοποίηση Views
        productNameTextView = findViewById(R.id.Product_Name);
        quantityEditText = findViewById(R.id.quantity_num);
        commentsEditText = findViewById(R.id.comment_sq);

        // Εμφάνιση δεδομένων
        productNameTextView.setText(itemName);
        quantityEditText.setText(String.valueOf(quantity));
        commentsEditText.setText(comments);

        // Κουμπί Ακύρωσης
        Button cancelButton = findViewById(R.id.button_back);
        cancelButton.setOnClickListener(v -> finish()); // Επιστροφή χωρίς αλλαγές

        // Κουμπί Αποθήκευσης
        Button saveButton = findViewById(R.id.button_payment);
        saveButton.setOnClickListener(v -> saveChanges());
    }

    private void saveChanges() {
        int newQuantity;
        try {
            newQuantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Εισάγετε έγκυρη ποσότητα", Toast.LENGTH_SHORT).show();
            return;
        }
        String newComments = commentsEditText.getText().toString();

        try {
            int rowsUpdated = SqlFinder.updateItem(tableNumber, itemName, newQuantity, newComments);
            if (rowsUpdated > 0) {
                Toast.makeText(this, "Οι αλλαγές αποθηκεύτηκαν", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Αποτυχία αποθήκευσης", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Σφάλμα βάσης δεδομένων", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
