package com.example.restaurantpda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemViewHolder> {

    private List<MenuItem> menuItems;

    public ProductAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;  // Αποθήκευση της λίστας προϊόντων
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Δημιουργία και επιστροφή της θέσης του στοιχείου (ViewHolder) για το RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Σύνδεση των δεδομένων από τη λίστα των προϊόντων με τα views στο ViewHolder
        MenuItem menuItem = menuItems.get(position);
        holder.nameTextView.setText(menuItem.getName());  // Εμφανίζει το όνομα του προϊόντος
        holder.quantityTextView.setText(String.valueOf(menuItem.getQuantity()));  // Εμφανίζει την ποσότητα
    }

    @Override
    public int getItemCount() {
        return menuItems.size();  // Επιστρέφει το μέγεθος της λίστας των προϊόντων
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView quantityTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);  // Σύνδεση με το TextView για το όνομα του προϊόντος
            quantityTextView = itemView.findViewById(R.id.item_quantity);  // Σύνδεση με το TextView για την ποσότητα
        }
    }
}
