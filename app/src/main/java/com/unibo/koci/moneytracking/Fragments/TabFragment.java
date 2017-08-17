package com.unibo.koci.moneytracking.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unibo.koci.moneytracking.Adapters.MoneyItemAdapter;
import com.unibo.koci.moneytracking.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by koale on 12/08/17.
 */

public class TabFragment extends Fragment {


    public static TabFragment newInstance(int someInt) {
        TabFragment myFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);
        return myFragment;
    }



    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("ciao alessio", Integer.toString(getArguments().getInt("someInt", 0)) );
        View rootView = inflater.inflate(R.layout.fragment_tab_money_item_list, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add("Test" + Integer.toString(getArguments().getInt("someInt", 0)) + " - " + i);
        }// define an adapter
        MoneyItemAdapter adapter = new MoneyItemAdapter(input);

        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;

    }


}