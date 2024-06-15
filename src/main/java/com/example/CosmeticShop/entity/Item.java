package com.example.CosmeticShop.entity;

public class Item {
    private Cosmetic cosmetic;
    private int quantity;
    public Item() {
    }

    public Item(Cosmetic cosmetic, int quantity) {
        this.cosmetic = cosmetic;
        this.quantity = quantity;
    }

    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}

