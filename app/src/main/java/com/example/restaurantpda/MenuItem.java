package com.example.restaurantpda;

public class MenuItem {
    private String name;  // Όνομα του προϊόντος
    private int quantity;  // Ποσότητα του προϊόντος

    public MenuItem() {}  // Κενή μέθοδος για να επιτρέπεται η δημιουργία του αντικειμένου από τη βάση δεδομένων Firebase

    public MenuItem(String name, int quantity) {
        this.name = name;  // Ανάθεση τιμών στις μεταβλητές
        this.quantity = quantity;
    }

    public String getName() {
        return name;  // Επιστροφή του ονόματος του προϊόντος
    }

    public int getQuantity() {
        return quantity;  // Επιστροφή της ποσότητας του προϊόντος
    }
}
