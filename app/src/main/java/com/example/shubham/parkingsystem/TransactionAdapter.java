package com.example.shubham.parkingsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shubham on 17-06-2017.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    List<Transaction> transactions;
    private String rupees="Rs.";
    public TransactionAdapter(Context context,List<Transaction> transactions) {
        super(context,0,transactions);
        this.transactions=transactions;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.transaction_row_layout,parent,false);
        }
        Transaction current=transactions.get(position);
        TextView costView=(TextView)listItemView.findViewById(R.id.cost_test_view);
        TextView dealerNameView=(TextView)listItemView.findViewById(R.id.dealer_name_text_view);
        TextView dealerNumberView=(TextView)listItemView.findViewById(R.id.dealer_num_text_view);
        DecimalFormat formatter=new DecimalFormat("0.0");
        String costString=rupees+formatter.format(current.getCost());
        costView.setText(costString);
        //costView.setText(Double.toString(current.getCost()));
        dealerNameView.setText(current.getDealerName());
        dealerNumberView.setText(current.getDealerNumber());
        return listItemView;
    }
}
