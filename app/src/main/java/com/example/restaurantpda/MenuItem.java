package com.example.restaurantpda;

public class MenuItem {
    private String name;  // Όνομα του προϊόντος
    private int quantity;  // Ποσότητα του προϊόντος
    private String category; // Προαιρετική κατηγορία

    // Constructors
    public MenuItem() {}

    public MenuItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public MenuItem(String name, int quantity, String category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    // Setters (αν χρειάζονται)
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
