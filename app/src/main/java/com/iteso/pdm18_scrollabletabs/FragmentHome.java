package com.iteso.pdm18_scrollabletabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm18_scrollabletabs.Controllers.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.tools.Constant;
import com.iteso.pdm18_scrollabletabs.tools.DataBaseHandler;

import java.util.ArrayList;
public class FragmentHome extends Fragment {


    RecyclerView recyclerView;
    ArrayList<ItemProduct> products;
    AdapterProduct adapterProduct;
    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_recycler);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        products = ItemProductControl.getItemProductsByCategoryId(2, dh);
        adapterProduct = new AdapterProduct(Constant.FRAGMENT_HOME, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }

    @Override
    public void onStart() {
        super.onStart();
        DataBaseHandler dh = DataBaseHandler.getInstance(getContext());
        products = ItemProductControl.getItemProductsByCategoryId(2, dh);
        adapterProduct = new AdapterProduct(Constant.FRAGMENT_HOME, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }
}