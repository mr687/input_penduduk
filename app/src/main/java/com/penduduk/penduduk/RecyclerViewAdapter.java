package com.penduduk.penduduk;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Penduduk> penduduks;
    public Context context;
    Utils utils = new Utils();
    public RecyclerViewAdapter(List<Penduduk> penduduks,Context context) {
        this.penduduks = penduduks;
        this.context  = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        final Penduduk pen = penduduks.get(i);
        holder.txtIDRuta.setText(pen.id_ruta+"");
        holder.txtNama.setText(pen.b1_k8);
        holder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(utils.isOnline()){
                    try {
                        sendPenduduk(context,pen);
                    } catch (JSONException e) {
                        Log.d("TAGG",e.getMessage());
                    }
                }else{
                    Toast.makeText(context,"Mohon untuk terhubung ke internet sebelum mengirim data.",Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,)
            }
        });
    }
    private void editPenduduk(){

    }
    private void sendPenduduk(Context context, Penduduk dataPenduduk) throws JSONException {
        HashMap data = new HashMap();
        data.put("id_ruta",dataPenduduk.id_ruta);
        data.put("b1_k1a",dataPenduduk.b1_k1a);
        data.put("b1_k1b",dataPenduduk.b1_k1b);
        data.put("b1_k2a",dataPenduduk.b1_k2a);
        data.put("b1_k2b",dataPenduduk.b1_k2b);
        data.put("b1_k3a",dataPenduduk.b1_k3a);
        data.put("b1_k3b",dataPenduduk.b1_k3b);
        data.put("b1_k4a",dataPenduduk.b1_k4a);
        data.put("b1_k4b",dataPenduduk.b1_k4b);
        data.put("b1_k5",dataPenduduk.b1_k5);
        data.put("b1_k6",dataPenduduk.b1_k6);
        data.put("b1_k8",dataPenduduk.b1_k8);
        data.put("b1_k9",dataPenduduk.b1_k9);
        data.put("b1_k10",dataPenduduk.b1_k10);
        data.put("b3_k1a",dataPenduduk.b3_k1a);
        data.put("b3_k1b",dataPenduduk.b3_k1b);
        data.put("b3_k2",dataPenduduk.b3_k2);
        data.put("b3_k3",dataPenduduk.b3_k3);
        data.put("b3_k4a",dataPenduduk.b3_k4a);
        data.put("b3_k4b",dataPenduduk.b3_k4b);
        data.put("b3_k5a",dataPenduduk.b3_k5a);
        data.put("b3_k5b",dataPenduduk.b3_k5b);
        data.put("b3_k6",dataPenduduk.b3_k6);
        data.put("b3_k7",dataPenduduk.b3_k7);
        data.put("b3_k8",dataPenduduk.b3_k8);
        data.put("b3_k9a",dataPenduduk.b3_k9a);
        data.put("b3_k9b",dataPenduduk.b3_k9b);
        data.put("b3_k10",dataPenduduk.b3_k10);
        data.put("b3_k11a",dataPenduduk.b3_k11a);
        data.put("b3_k11b",dataPenduduk.b3_k11b);
        data.put("b3_k12",dataPenduduk.b3_k12);
        data.put("b5_k1a",dataPenduduk.b5_k1a);
        data.put("b5_k1b",dataPenduduk.b5_k1b);
        data.put("b5_k1c",dataPenduduk.b5_k1c);
        data.put("b5_k1d",dataPenduduk.b5_k1d);
        data.put("b5_k1e",dataPenduduk.b5_k1e);
        data.put("b5_k1f",dataPenduduk.b5_k1f);
        data.put("b5_k1g",dataPenduduk.b5_k1g);
        data.put("b5_k1h",dataPenduduk.b5_k1h);
        data.put("b5_k1i",dataPenduduk.b5_k1i);
        data.put("b5_k1j",dataPenduduk.b5_k1j);
        data.put("b5_k1k",dataPenduduk.b5_k1k);
        data.put("b5_k1l",dataPenduduk.b5_k1l);
        data.put("b5_k1m",dataPenduduk.b5_k1m);
        data.put("b5_k1n",dataPenduduk.b5_k1n);
        data.put("b5_k1o",dataPenduduk.b5_k1o);
        data.put("b5_k2a1",dataPenduduk.b5_k2a1);
        data.put("b5_k2a2",dataPenduduk.b5_k2a2);
        data.put("b5_k2b",dataPenduduk.b5_k2b);
        data.put("b5_k3a",dataPenduduk.b5_k3a);
        data.put("b5_k3b",dataPenduduk.b5_k3b);
        data.put("b5_k3c",dataPenduduk.b5_k3c);
        data.put("b5_k3d",dataPenduduk.b5_k3d);
        data.put("b5_k3e",dataPenduduk.b5_k3e);
        data.put("b5_k4a",dataPenduduk.b5_k4a);
        data.put("b5_k5a",dataPenduduk.b5_k5a);
        data.put("b5_k5b",dataPenduduk.b5_k5b);
        data.put("b5_k5c",dataPenduduk.b5_k5c);
        data.put("b5_k5d",dataPenduduk.b5_k5d);
        data.put("b5_k5e",dataPenduduk.b5_k5e);
        data.put("b5_k5f",dataPenduduk.b5_k5f);
        data.put("b5_k5g",dataPenduduk.b5_k5g);
        data.put("b5_k5h",dataPenduduk.b5_k5h);
        data.put("b5_k5i",dataPenduduk.b5_k5i);

        JSONObject allObject = new JSONObject();
        for (int i =0;i<dataPenduduk.artData.size();i++){
            DataART dataART = dataPenduduk.artData.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("b4_k1",dataART.b4_k1);
            jsonObject.put("b4_k2a",dataART.b4_k2a);
            jsonObject.put("b4_k2b",dataART.b4_k2b);
            jsonObject.put("b4_k3",dataART.b4_k3);
            jsonObject.put("b4_k4",dataART.b4_k4);
            jsonObject.put("b4_k5",dataART.b4_k5);
            jsonObject.put("b4_k6",dataART.b4_k6);
            jsonObject.put("b4_k7",dataART.b4_k7);
            jsonObject.put("b4_k8",dataART.b4_k8);
            jsonObject.put("b4_k9",dataART.b4_k9);
            jsonObject.put("b4_k10",dataART.b4_k10);
            jsonObject.put("b4_k11",dataART.b4_k11);
            jsonObject.put("b4_k12",dataART.b4_k12);
            jsonObject.put("b4_k13",dataART.b4_k13);
            jsonObject.put("b4_k14",dataART.b4_k14);
            jsonObject.put("b4_k15",dataART.b4_k15);
            jsonObject.put("b4_k16",dataART.b4_k16);
            jsonObject.put("b4_k17",dataART.b4_k17);
            jsonObject.put("b4_k18",dataART.b4_k18);
            jsonObject.put("b4_k19a",dataART.b4_k19a);
            jsonObject.put("b4_k19b",dataART.b4_k19b);
            jsonObject.put("b4_k20",dataART.b4_k20);
            jsonObject.put("b4_k21",dataART.b4_k21);
            allObject.put((i)+"",jsonObject);
        }
        if(dataPenduduk.artData.size()>0){
            data.put("artData",allObject);
        }else{
            data.put("artData",0);
        }
        utils.postData(EndPoint.ADDPENDUDUK_URL,data,context);
    }
    @Override
    public int getItemCount() {
        return penduduks.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtIDRuta;
        TextView txtNama;
        Button btnEdit,btnSend;
        Utils utils = new Utils();
        public MyViewHolder(final View itemView) {
            super(itemView);

            txtIDRuta = (TextView) itemView.findViewById(R.id.txtIDRuta);
            txtNama = (TextView) itemView.findViewById(R.id.txtNama);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnSend = (Button) itemView.findViewById(R.id.btnSend);
            if(utils.isOnline()){
                btnEdit.setVisibility(View.VISIBLE);
                btnSend.setVisibility(View.GONE);
            }else{
                btnSend.setVisibility(View.VISIBLE);
                btnEdit.setVisibility(View.GONE);
            }
        }
    }
}
