package com.example.CosmeticShop.entity;

import java.util.ArrayList;

public class Cart {
    ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    private Item getItemById(int id){
        for (Item i : items) {
            if (i.getCosmetic().getId()==id){
                return i;
            }
        }
        return null;
    }
    public void addItem(Item t ){
        if (getItemById(t.getCosmetic().getId())!=null){
            Item m = getItemById(t.getCosmetic().getId());
            m.setQuantity(m.getQuantity()+t.getQuantity());          
        }
        else{
            items.add(t);
        }
    }
    public void removeItem(int id){
        if (getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }

    private Cosmetic getCosmeticById(int id,ArrayList<Cosmetic> list){
        for (Cosmetic i : list) {
            if (i.getId()==id){
                return i;
            }
        }
        return null;
    }

}
