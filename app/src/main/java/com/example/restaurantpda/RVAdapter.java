package com.example.restaurantpda;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MenuViewHolder> {

    private List<MenuItem> menuItems;
    private String tableNumber; // Αποθήκευση αριθμού τραπεζιού

    public RVAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.tableNumber = tableNumber; // Αρχικοποίηση
    }

    public void updateMenuItems(List<MenuItem> newItems) {
        this.menuItems = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        // Δέσμευση δεδομένων στο ViewHolder
        holder.nameTextView.setText(item.getName());
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

        // Άνοιγμα EditProduct Activity κατά το κλικ
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), EditProduct.class);
            intent.putExtra("TABLE_NUMBER", tableNumber);
            intent.putExtra("ITEM_NAME", item.getName());
            intent.putExtra("QUANTITY", item.getQuantity());
            intent.putExtra("COMMENTS", "No Comments");
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return menuItems != null ? menuItems.size() : 0;
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView quantityTextView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            quantityTextView = itemView.findViewById(R.id.item_quantity);
        }
    }
}
