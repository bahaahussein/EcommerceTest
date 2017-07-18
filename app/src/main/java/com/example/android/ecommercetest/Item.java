package com.example.android.ecommercetest;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Professor on 7/17/2017.
 */

public class Item implements Parcelable {

    private int photo;
    private int discount;
    private String name;
    private double price;
    private double oldPrice;

    public Item(int photo, int discount, String name, double price, double oldPrice) {
        this.photo = photo;
        this.discount = discount;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public int getPhoto() {
        return photo;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "name: " + name + ", price: " + price + ", old price: " + oldPrice + ", discount: "
                + discount + ", photo: " + photo;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    protected Item(Parcel in) {
        photo = in.readInt();
        discount = in.readInt();
        name = in.readString();
        price = in.readDouble();
        oldPrice = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeInt(discount);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeDouble(oldPrice);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
