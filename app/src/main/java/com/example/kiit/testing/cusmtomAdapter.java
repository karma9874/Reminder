package com.example.kiit.testing;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;


public class cusmtomAdapter extends BaseAdapter{
    private final ArrayList<Customers> customers;
    private Context context;
    SharedPreferences.Editor editor;
    private LayoutInflater inflater;
    static boolean checkboxvalue = false;


    public cusmtomAdapter(ArrayList<Customers> customers, Context context) {
        this.customers = customers;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void add(Customers customer){
        customers.add(customer);
        notifyDataSetChanged();
    }

    public void remove(int customer){
        customers.remove(customer);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int i) {
        return customers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
         final CustomerViewHolder viewHolder;
        SharedPreferences sharedPrefs = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        if(view == null){
            view = inflater.inflate(R.layout.single_row,viewGroup,false);
                viewHolder = new CustomerViewHolder();
            viewHolder.FirstName = view.findViewById(R.id.textview1);
            viewHolder.checkBox = view.findViewById(R.id.chekbox);
            view.setTag(viewHolder);
        }else{
            viewHolder = (CustomerViewHolder)view.getTag();
        }
        editor = sharedPrefs.edit();
        viewHolder.FirstName.setText(customers.get(i).getFirstName());
        viewHolder.checkBox.setChecked(sharedPrefs.getBoolean("CheckValue"+i,checkboxvalue));
        viewHolder.checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checkboxvalue) {
                        editor.putBoolean("CheckValue"+i,checkboxvalue);
                        editor.apply();
                    }
                }
        );
        return view;
    }

    private class CustomerViewHolder{
        TextView FirstName;
        CheckBox checkBox;
    }
}
