package com.penduduk.penduduk;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DataARTAdapter extends ArrayAdapter<DataART> {
    private Context context;
    private ArrayList<DataART> listART;
    public DataARTAdapter(Context context, ArrayList<DataART> listART) {
        super(context,0,listART);
        this.context = context;
        this.listART = listART;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        DataART dataART = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_data_art,parent,false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvNIK = (TextView) convertView.findViewById(R.id.tvNIK);
        tvName.setText(dataART.b4_k2a);
        tvNIK.setText(dataART.b4_k2b);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
