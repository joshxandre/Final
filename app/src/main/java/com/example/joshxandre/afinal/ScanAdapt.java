package com.example.joshxandre.afinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScanAdapt extends ArrayAdapter<ShoppingC>
{
    private Context mContext;
    private int mResLayoutId;
    private List<ShoppingC> ShopCart;

    public ScanAdapt (Context context, int resLayoutId, List<ShoppingC> shopp){
        super(context, resLayoutId, shopp);
        mContext = context;
        mResLayoutId = resLayoutId;
        ShopCart = shopp;
    }

    private class ViewHolder{
        TextView tvItem;
        TextView tvQuantity;
        TextView tvPrice;
        TextView tvTotal;

        public ViewHolder(View view){
            tvItem = (TextView) view.findViewById(R.id.itemName);
            tvQuantity = (TextView) view.findViewById(R.id.Quantity);
            tvPrice = (TextView) view.findViewById(R.id.Price);
            tvTotal = (TextView) view.findViewById(R.id.TotalPrice);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(mResLayoutId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);} else {holder = (ViewHolder)convertView.getTag();}
        ShoppingC shop = ShopCart.get(position);
        if (shop!=null) {
            if (holder.tvItem != null){
                holder.tvItem.setText("Item Name: "+shop.getItemName()+"");}
            if (holder.tvQuantity !=null){
                holder.tvQuantity.setText("Quantity: "+shop.getQty()+"");}
            if (holder.tvPrice !=null){
                holder.tvPrice.setText("Price: "+(int) shop.getPrice()+"");}
            if (holder.tvTotal !=null){
                holder.tvTotal.setText("Total Price: "+(int) shop.getTotalPrice()+"");}}
        return convertView;
    }



}
