package com.example.android.ecommercetest;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private Item item;
    private TextView name, price, oldPrice, discount;
    private ImageView image;
    private Button addToDatabase;
    private DatabaseReference myRef;
    private RatingBar ratingBar;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRef = FirebaseDatabase.getInstance().getReference().child("items");
        name = (TextView)view.findViewById(R.id.detail_name);
        price = (TextView)view.findViewById(R.id.detail_price);
        oldPrice = (TextView)view.findViewById(R.id.detail_oldPrice);
        discount = (TextView)view.findViewById(R.id.detail_discount);
        image = (ImageView)view.findViewById(R.id.detail_photo);
        addToDatabase = (Button)view.findViewById(R.id.detail_button);
        ratingBar = (RatingBar) view.findViewById(R.id.detail_rating);

        Bundle bundle = getArguments();
        item = bundle.getParcelable(MainActivity.ITEM_KEY);

        name.setText(item.getName());
        price.setText(item.getPrice() + " EGP");
        oldPrice.setText(item.getOldPrice() + " EGP");
        oldPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        discount.setText(item.getDiscount() + "% OFF");
        image.setImageResource(item.getPhoto());

        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(item.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Toast.makeText(getActivity(),"This item exists",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            DatabaseReference myRefrence = myRef.child(item.getName());
                            myRefrence.child("price").setValue("" + item.getPrice());
                            myRefrence.child("old price").setValue("" + item.getOldPrice());
                            myRefrence.child("discount").setValue("" + item.getDiscount());
                            myRefrence.child("photo").setValue(item.getPhoto());
                            myRefrence.child("rating").setValue(ratingBar.getRating());
                            Toast.makeText(getActivity(),"Done we did it :D",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}
