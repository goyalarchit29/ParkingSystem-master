package com.example.shubham.parkingsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 15-06-2017.
 */
public class Fragment_B extends Fragment {
    private View rootView;
    private ListView listView;
    private TransactionAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_b,container,false);
        listView= (ListView)rootView.findViewById(R.id.transaction_list);
        String name="Rahul Somani";
        String number="9898765432";
        Transaction t=new Transaction(15.0,name,number);
        Transaction k=new Transaction(11.0,"Archit Goyal","567890457");
        List<Transaction> transactions=new ArrayList<Transaction>();
        transactions.add(t);
        transactions.add(k);
        adapter=new TransactionAdapter(getActivity(),transactions);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
