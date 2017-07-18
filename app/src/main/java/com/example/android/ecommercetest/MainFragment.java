package com.example.android.ecommercetest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.ecommercetest.MainActivity.ITEM_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ListListener{

    private static final String LOG_TAG = MainFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ArrayList<Item> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setData();
        MyAdapter adapter = new MyAdapter(items);
        adapter.setListener(this);
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void setData() {
        items = new ArrayList<Item>();
        Item item = new Item(R.drawable.nespresso, 25, "nespresso inissia and aeroccino3", 3600, 4800);
        items.add(item);
        item = new Item(R.drawable.alcatel, 16, "alcatel delta 180", 386, 458);
        items.add(item);
        Log.e(LOG_TAG, items.toString());
    }

    @Override
    public void OnClick(int itemPosition) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_KEY, items.get(itemPosition));
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, detailFragment);
        transaction.addToBackStack(null).commit();
    }
}
