package com.example.joshxandre.afinal;

/**
 * Created by Joshxandre on 07/09/2016.
 */
public class ShoppingC {
    private String ItemName;
    private int Qty;
    private double Price;
    private double TotalPrice;

    public ShoppingC(String ItemName, int qty, double price, double totalPrice) {

        ItemName = ItemName;
        Qty = qty;
        Price = price;
        TotalPrice = totalPrice;
    }


    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String ItemName) {
        ItemName = ItemName;
    }
    public int getQty() {
        return Qty;
    }
    public void setQty(int qty) {
        Qty = qty;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

}
