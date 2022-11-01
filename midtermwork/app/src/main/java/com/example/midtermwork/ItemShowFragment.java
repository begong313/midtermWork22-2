package com.example.midtermwork;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ItemShowFragment extends Fragment {
    RecyclerView recyclerView;
    ItemAdapter recyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_itemshow, container, false);

        //recyclerView control
        recyclerView = (RecyclerView) v.findViewById(R.id.itemRecycler);
        recyclerAdapter = new ItemAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //test
        ArrayList<Item> testData = new ArrayList<Item>();
        testData.add(new Item("김밥","3500원", R.drawable.kimbob));
        testData.add(new Item("김치 볶음밥","5000원", R.drawable.kimchirice));
        testData.add(new Item("라면","4000원",R.drawable.ramen));
        testData.add(new Item("돈까스","7000원",R.drawable.curtlet));
        testData.add(new Item("우동","4000원",R.drawable.udong));
        testData.add(new Item("떡볶이","4500원",R.drawable.dducboke));
        recyclerAdapter.setItemData(testData);


        // Inflate the layout for this fragment
        return v;
    }
}