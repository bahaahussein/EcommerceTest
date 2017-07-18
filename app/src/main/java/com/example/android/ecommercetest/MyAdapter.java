package com.example.android.ecommercetest;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Professor on 7/17/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private ListListener listener;


    public MyAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    public void setListener(ListListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        holder.discount.setText(item.getDiscount()+"% OFF");
        holder.price.setText(item.getPrice()+ " EGP");
        holder.oldPrice.setText(item.getOldPrice() + " EGP");
        holder.oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.photo.setImageResource(item.getPhoto());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, discount, price, oldPrice;
        public ImageView photo;
        public Button addToCart;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.list_item_name);
            discount = (TextView)itemView.findViewById(R.id.list_item_discount);
            price = (TextView)itemView.findViewById(R.id.list_item_price);
            photo = (ImageView)itemView.findViewById(R.id.list_item_photo);
            oldPrice = (TextView) itemView.findViewById(R.id.list_item_oldPrice);
            addToCart = (Button) itemView.findViewById(R.id.list_item_button);

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.OnClick(getAdapterPosition());
                }
            });
        }
    }
}
