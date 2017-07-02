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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by archi on 27-06-2017.
 */

public class DealerAdapter extends ArrayAdapter<Dealer> {

    List<Dealer> transactions;
    private String rupees="Rs.";
    public DealerAdapter(Context context, List<Dealer> transactions) {
        super(context, 0, transactions);
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.dealers_row_layout,parent,false);
        }
        Dealer current=transactions.get(position);
        TextView costView=(TextView)listItemView.findViewById(R.id.dealer_cost_view);
        TextView cusNameView=(TextView)listItemView.findViewById(R.id.cus_name_view);
        TextView cusNumberView=(TextView)listItemView.findViewById(R.id.cus_num_view);
        DecimalFormat formatter=new DecimalFormat("0.0");
        String costString=rupees+formatter.format(current.getCost());
        costView.setText(costString);
        //costView.setText(Double.toString(current.getCost()));
        cusNameView.setText(current.getCusName());
        cusNumberView.setText(current.getCusNumber());
        return listItemView;
    }
}
