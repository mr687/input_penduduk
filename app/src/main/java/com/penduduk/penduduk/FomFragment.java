package com.penduduk.penduduk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.penduduk.penduduk.Utils.AUTH_SESSION;

public class FomFragment extends Fragment
        implements View.OnClickListener,AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener {
    LinearLayout layoutDindingTerluas,layoutAtapTerluas, layoutSumberPeneranganUtama,layoutLahan;
    Button btnTambahART;
    Utils utils = new Utils();
    SharedPreferences sharedPreferences;
    EditText b1_k1a,b1_k2a,b1_k3a,b1_k4a,b1_k5,b1_k6,b1_k8,b1_k9,b1_k10,b3_k2,b3_k6;
    Spinner b3_k1a,b3_k1b,b3_k3,b3_k4a,b3_k4b,b3_k5a,b3_k5b,b3_k7,b3_k8,b3_k9a,b3_k9b,b3_k10,b3_k11a,b3_k11b,b3_k12;
    CheckBox b5_k1a,b5_k1b,b5_k1c,b5_k1d,b5_k1e,b5_k1f,b5_k1g,b5_k1h,b5_k1i,
            b5_k1j,b5_k1k,b5_k1l,b5_k1m,b5_k1n,b5_k1o,b5_k4a,b5_k5a,b5_k5b,b5_k5c,b5_k5d,
            b5_k5e,b5_k5f,b5_k5g,b5_k5h,b5_k5i,b5_k2a1,b5_k2b;
    EditText b5_k3a,b5_k3b,b5_k3c,b5_k3d,b5_k3e,b5_k2a2,b1_k4b,b1_k3b,txtKodeSLS,txtNoUrutRumahTangga;
    ListView listViewART;
    String b4_k2a,id_ruta,b4_k2b,b4_k19b;
    int b4_k1,b4_k3,b4_k4,b4_k5,b4_k6, b4_k7,b4_k8,b4_k9,b4_k10,b4_k11,
            b4_k12,b4_k13,b4_k14,b4_k15,b4_k16,b4_k17,b4_k18,b4_k19a,b4_k20,b4_k21,b1_k1b,b1_k2b;
    Button btnSimpan,btnBatal;
    DBHelper helper;
    ProgressDialog progressDialog;

    public ArrayList<DataART> listART = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    public FomFragment() {
    }

//    public static FomFragment newInstance(String param1, String param2) {
//        FomFragment fragment = new FomFragment();
//        return fragment;
//    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view,
                             Bundle savedInstanceState) {
        View container = inflater.inflate(R.layout.fragment_fom, view, false);
        getActivity().setTitle("Form Pendataan Penduduk");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");

        helper = new DBHelper(getActivity());
        sharedPreferences = getActivity().getSharedPreferences(AUTH_SESSION,Context.MODE_PRIVATE);
        btnTambahART = (Button) container.findViewById(R.id.btnTambahART);
        listViewART = (ListView) container.findViewById(R.id.listViewART);
        btnSimpan = (Button) container.findViewById(R.id.btnSimpan);
        btnBatal = (Button) container.findViewById(R.id.btnBatal);
        txtKodeSLS = (EditText) container.findViewById(R.id.txtKodeSLS);
        txtNoUrutRumahTangga = (EditText) container.findViewById(R.id.txtNomorUrutKeluarga);
        layoutDindingTerluas = (LinearLayout) container.findViewById(R.id.layoutKondisiDinding);
        layoutAtapTerluas = (LinearLayout) container.findViewById(R.id.layoutAtapTerluas);
        layoutSumberPeneranganUtama = (LinearLayout) container.findViewById(R.id.layoutSumberPeneranganUtama);
        layoutLahan = (LinearLayout) container.findViewById(R.id.layoutLahan);
//        130601000100110001
        b1_k1a =(EditText) container.findViewById(R.id.b1_k1a);
        b1_k1b = 13;
        b1_k2a = (EditText) container.findViewById(R.id.b1_k2a);
        b1_k2b = 06;
        b1_k3a = (EditText) container.findViewById(R.id.b1_k3a);
        b1_k3a.setText(sharedPreferences.getString("kecamatan",null));
        b1_k3b = (EditText) container.findViewById(R.id.b1_k3b);
        b1_k4a = (EditText) container.findViewById(R.id.b1_k4a);
        b1_k4a.setText(sharedPreferences.getString("desa",null));
        b1_k4b = (EditText) container.findViewById(R.id.b1_k4b);
        b1_k5 = (EditText) container.findViewById(R.id.b1_k5);
        b1_k6 = (EditText) container.findViewById(R.id.b1_k6);
        b1_k8 = (EditText) container.findViewById(R.id.b1_k8);
        b1_k9 = (EditText) container.findViewById(R.id.b1_k9);
        b1_k10 = (EditText) container.findViewById(R.id.b1_k10);
        b3_k1a = (Spinner) container.findViewById(R.id.b3_k1a);
        b3_k1b = (Spinner) container.findViewById(R.id.b3_k1b);
        b3_k2 = (EditText) container.findViewById(R.id.b3_k2);
        b3_k3 = (Spinner) container.findViewById(R.id.b3_k3);
        b3_k4a = (Spinner) container.findViewById(R.id.b3_k4a);
        b3_k4b = (Spinner) container.findViewById(R.id.b3_k4b);
        b3_k5a = (Spinner) container.findViewById(R.id.b3_k5a);
        b3_k5b = (Spinner) container.findViewById(R.id.b3_k5b);
        b3_k6 = (EditText) container.findViewById(R.id.b3_k6);
        b3_k7 = (Spinner) container.findViewById(R.id.b3_k7);
        b3_k8 = (Spinner) container.findViewById(R.id.b3_k8);
        b3_k9a = (Spinner) container.findViewById(R.id.b3_k9a);
        b3_k9b = (Spinner) container.findViewById(R.id.b3_k9b);
        b3_k10 = (Spinner) container.findViewById(R.id.b3_k10);
        b3_k11a = (Spinner) container.findViewById(R.id.b3_k11a);
        b3_k11b = (Spinner) container.findViewById(R.id.b3_k11b);
        b3_k12 = (Spinner) container.findViewById(R.id.b3_k12);
        b5_k1a = (CheckBox) container.findViewById(R.id.checkTabungGas);
        b5_k1b = (CheckBox) container.findViewById(R.id.checkKulkas);
        b5_k1c = (CheckBox) container.findViewById(R.id.checkAC);
        b5_k1d = (CheckBox) container.findViewById(R.id.checkPemanasAir);
        b5_k1e = (CheckBox) container.findViewById(R.id.checkTeleponRumah);
        b5_k1f = (CheckBox) container.findViewById(R.id.checkTelevisi);
        b5_k1g = (CheckBox) container.findViewById(R.id.checkPerhiasan);
        b5_k1h = (CheckBox) container.findViewById(R.id.checkKomputer);
        b5_k1i = (CheckBox) container.findViewById(R.id.checkSepeda);
        b5_k1j = (CheckBox) container.findViewById(R.id.checkSepedaMotor);
        b5_k1k = (CheckBox) container.findViewById(R.id.checkMobil);
        b5_k1l = (CheckBox) container.findViewById(R.id.checkPerahu);
        b5_k1m = (CheckBox) container.findViewById(R.id.checkMotorTempel);
        b5_k1n = (CheckBox) container.findViewById(R.id.checkPerahuMotor);
        b5_k1o = (CheckBox) container.findViewById(R.id.checkKapal);
        b5_k2a1 = (CheckBox) container.findViewById(R.id.checkLahan);
        b5_k2a2 = (EditText) container.findViewById(R.id.txtLuasLahan);
        b5_k2b = (CheckBox) container.findViewById(R.id.checkRumahTempatLain);
        b5_k3a = (EditText) container.findViewById(R.id.txtSapi);
        b5_k3b = (EditText) container.findViewById(R.id.txtKerbau);
        b5_k3c = (EditText) container.findViewById(R.id.txtKuda);
        b5_k3d = (EditText) container.findViewById(R.id.txtBabi);
        b5_k3e = (EditText) container.findViewById(R.id.txtKambingDomba);
        b5_k4a = (CheckBox) container.findViewById(R.id.checkUsahaSendiriBersama);
        b5_k5a = (CheckBox) container.findViewById(R.id.checkKKSKPS);
        b5_k5b = (CheckBox) container.findViewById(R.id.checkKIP);
        b5_k5c = (CheckBox) container.findViewById(R.id.checkKISBPJS);
        b5_k5d = (CheckBox) container.findViewById(R.id.checkBPSMandiri);
        b5_k5e = (CheckBox) container.findViewById(R.id.checkJamsostek);
        b5_k5f = (CheckBox) container.findViewById(R.id.checkAsuransiLainnya);
        b5_k5g = (CheckBox) container.findViewById(R.id.checkPKH);
        b5_k5h = (CheckBox) container.findViewById(R.id.checkRaskin);
        b5_k5i = (CheckBox) container.findViewById(R.id.checkKUR);

        b3_k4a.setOnItemSelectedListener(this);
        b3_k5a.setOnItemSelectedListener(this);
        b3_k9a.setOnItemSelectedListener(this);
        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);
        b5_k2a1.setOnCheckedChangeListener(this);

        btnTambahART.setOnClickListener(this);

        return container;
    }
    private void simpanPendudukOnline(String ruta_id) throws JSONException {
        HashMap data = new HashMap();
        data.put("id_ruta",ruta_id);
        data.put("b1_k1a",b1_k1a.getText().toString());
        data.put("b1_k1b",b1_k1b);
        data.put("b1_k2a",b1_k2a.getText().toString());
        data.put("b1_k2b",b1_k2b);
        data.put("b1_k3a",b1_k3a.getText().toString());
        data.put("b1_k3b",Integer.valueOf(b1_k3b.getText().toString()));
        data.put("b1_k4a",b1_k4a.getText().toString());
        data.put("b1_k4b",Integer.valueOf(b1_k4b.getText().toString()));
        data.put("b1_k5",b1_k5.getText().toString());
        data.put("b1_k6",b1_k6.getText().toString());
        data.put("b1_k8",b1_k8.getText().toString());
        data.put("b1_k9",Integer.valueOf(b1_k9.getText().toString()));
        data.put("b1_k10",Integer.valueOf(b1_k10.getText().toString()));
        data.put("b3_k1a",b3_k1a.getSelectedItemPosition()+1);
        data.put("b3_k1b",b3_k1b.getSelectedItemPosition()+1);
        data.put("b3_k2",b3_k2.getText().toString());
        data.put("b3_k3",Integer.valueOf(String.format("%02d",Integer.valueOf(b3_k3.getSelectedItemPosition()+1))));
        data.put("b3_k4a",b3_k4a.getSelectedItemPosition()+1);
        data.put("b3_k4b",b3_k4b.getSelectedItemPosition()+1);
        data.put("b3_k5a",Integer.valueOf(String.format("%02d",Integer.valueOf(b3_k5a.getSelectedItemPosition()+1))));
        data.put("b3_k5b",b3_k5b.getSelectedItemPosition()+1);
        data.put("b3_k6",Integer.valueOf(b3_k6.getText().toString()));
        data.put("b3_k7",Integer.valueOf(String.format("%02d",b3_k7.getSelectedItemPosition()+1)));
        data.put("b3_k8",b3_k8.getSelectedItemPosition()+1);
        data.put("b3_k9a",b3_k9a.getSelectedItemPosition()+1);
        data.put("b3_k9b",b3_k9b.getSelectedItemPosition()+1);
        data.put("b3_k10",b3_k10.getSelectedItemPosition()+1);
        data.put("b3_k11a",b3_k11a.getSelectedItemPosition()+1);
        data.put("b3_k11b",b3_k11b.getSelectedItemPosition()+1);
        data.put("b3_k12",b3_k12.getSelectedItemPosition()+1);
        data.put("b5_k1a",(b5_k1a.isChecked()) ? 1:2);
        data.put("b5_k1b",(b5_k1b.isChecked()) ? 3:4);
        data.put("b5_k1c",(b5_k1c.isChecked()) ? 1:2);
        data.put("b5_k1d",(b5_k1d.isChecked()) ? 3:4);
        data.put("b5_k1e",(b5_k1e.isChecked()) ? 1:2);
        data.put("b5_k1f",(b5_k1f.isChecked()) ? 3:4);
        data.put("b5_k1g",(b5_k1g.isChecked()) ? 1:2);
        data.put("b5_k1h",(b5_k1h.isChecked()) ? 3:4);
        data.put("b5_k1i",(b5_k1i.isChecked()) ? 1:2);
        data.put("b5_k1j",(b5_k1j.isChecked()) ? 3:4);
        data.put("b5_k1k",(b5_k1k.isChecked()) ? 1:2);
        data.put("b5_k1l",(b5_k1l.isChecked()) ? 3:4);
        data.put("b5_k1m",(b5_k1m.isChecked()) ? 1:2);
        data.put("b5_k1n",(b5_k1n.isChecked()) ? 3:4);
        data.put("b5_k1o",(b5_k1o.isChecked()) ? 1:2);
        data.put("b5_k2a1",(b5_k2a1.isChecked())?1:2);
        data.put("b5_k2a2",(b5_k2a1.isChecked())?Integer.valueOf(b5_k2a2.getText().toString()):0);
        data.put("b5_k2b",(b5_k2b.isChecked())?3:4);
        data.put("b5_k3a",Integer.valueOf(b5_k3a.getText().toString()));
        data.put("b5_k3b",Integer.valueOf(b5_k3b.getText().toString()));
        data.put("b5_k3c",Integer.valueOf(b5_k3c.getText().toString()));
        data.put("b5_k3d",Integer.valueOf(b5_k3d.getText().toString()));
        data.put("b5_k3e",Integer.valueOf(b5_k3e.getText().toString()));
        data.put("b5_k4a",(b5_k4a.isChecked())?1:2);
        data.put("b5_k5a",(b5_k5a.isChecked())?1:2);
        data.put("b5_k5b",(b5_k5b.isChecked())?3:4);
        data.put("b5_k5c",(b5_k5c.isChecked())?1:2);
        data.put("b5_k5d",(b5_k5d.isChecked())?3:4);
        data.put("b5_k5e",(b5_k5e.isChecked())?1:2);
        data.put("b5_k5f",(b5_k5f.isChecked())?3:4);
        data.put("b5_k5g",(b5_k5g.isChecked())?1:2);
        data.put("b5_k5h",(b5_k5h.isChecked())?3:4);
        data.put("b5_k5i",(b5_k5i.isChecked())?1:2);

        JSONObject allObject = new JSONObject();
        for (int i =0;i<listART.size();i++){
            DataART dataART = listART.get(i);
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
        if(listART.size()>0){
            data.put("artData",allObject);
        }else{
            data.put("artData",0);
        }
        data.put("uid",sharedPreferences.getString("uid",null));
        utils.postData(EndPoint.ADDPENDUDUK_URL,data,getActivity().getBaseContext());
        progressDialog.hide();
    }
    private void simpanPendudukOffline(String ruta_id){
        ruta_id = ruta_id;
        for (int i = 0;i<listART.size();i++){
            DataART data = listART.get(i);
            helper.addART(ruta_id, (i+1), data.b4_k2a, data.b4_k2b, data.b4_k3, data.b4_k4, data.b4_k5, data.b4_k6,
                    data.b4_k7,data.b4_k8,data.b4_k9,data.b4_k10,data.b4_k11,data.b4_k12, data.b4_k13,data.b4_k14,
                    data.b4_k15,data.b4_k16,data.b4_k17,data.b4_k18,data.b4_k19a,data.b4_k19b,data.b4_k20, data.b4_k21);
        }
        helper.addPlayers(
                ruta_id,
                b1_k1a.getText().toString(),
                b1_k1b,
                b1_k2a.getText().toString(),
                b1_k2b,
                b1_k3a.getText().toString(),
                Integer.valueOf(b1_k3b.getText().toString()),
                b1_k4a.getText().toString(),
                Integer.valueOf(b1_k4b.getText().toString()),
                b1_k5.getText().toString(),
                b1_k6.getText().toString(),
                b1_k8.getText().toString(),
                Integer.valueOf(b1_k9.getText().toString()),
                Integer.valueOf(b1_k10.getText().toString()),
                b3_k1a.getSelectedItemPosition()+1,
                b3_k1b.getSelectedItemPosition()+1,
                b3_k2.getText().toString(),
                Integer.valueOf(String.format("%02d",Integer.valueOf(b3_k3.getSelectedItemPosition()+1))),
                b3_k4a.getSelectedItemPosition()+1,
                b3_k4b.getSelectedItemPosition()+1,
                Integer.valueOf(String.format("%02d",Integer.valueOf(b3_k5a.getSelectedItemPosition()+1))),
                b3_k5b.getSelectedItemPosition()+1,Integer.valueOf(b3_k6.getText().toString()),
                Integer.valueOf(String.format("%02d",b3_k7.getSelectedItemPosition()+1)),
                b3_k8.getSelectedItemPosition()+1,
                b3_k9a.getSelectedItemPosition()+1,
                b3_k9b.getSelectedItemPosition()+1,
                b3_k10.getSelectedItemPosition()+1,
                b3_k11a.getSelectedItemPosition()+1,
                b3_k11b.getSelectedItemPosition()+1,
                b3_k12.getSelectedItemPosition()+1,
                (b5_k1a.isChecked()) ? 1:2,
                (b5_k1b.isChecked()) ? 3:4,
                (b5_k1c.isChecked()) ? 1:2,
                (b5_k1d.isChecked()) ? 3:4,
                (b5_k1e.isChecked()) ? 1:2,
                (b5_k1f.isChecked()) ? 3:4,
                (b5_k1g.isChecked()) ? 1:2,
                (b5_k1h.isChecked()) ? 3:4,
                (b5_k1i.isChecked()) ? 1:2,
                (b5_k1j.isChecked()) ? 3:4,
                (b5_k1k.isChecked()) ? 1:2,
                (b5_k1l.isChecked()) ? 3:4,
                (b5_k1m.isChecked()) ? 1:2,
                (b5_k1n.isChecked()) ? 3:4,
                (b5_k1o.isChecked()) ? 1:2,
                (b5_k2a1.isChecked())?1:2,
                (b5_k2a1.isChecked())?Integer.valueOf(b5_k2a2.getText().toString()):0,
                (b5_k2b.isChecked())?3:4,
                Integer.valueOf(b5_k3a.getText().toString()),
                Integer.valueOf(b5_k3b.getText().toString()),
                Integer.valueOf(b5_k3c.getText().toString()),
                Integer.valueOf(b5_k3d.getText().toString()),
                Integer.valueOf(b5_k3e.getText().toString()),
                (b5_k4a.isChecked())?1:2,
                (b5_k5a.isChecked())?1:2,
                (b5_k5b.isChecked())?3:4,
                (b5_k5c.isChecked())?1:2,
                (b5_k5d.isChecked())?3:4,
                (b5_k5e.isChecked())?1:2,
                (b5_k5f.isChecked())?3:4,
                (b5_k5g.isChecked())?1:2,
                (b5_k5h.isChecked())?3:4,
                (b5_k5i.isChecked())?1:2
                );
        progressDialog.hide();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==b3_k4a){
            if(position<=2){
                layoutDindingTerluas.setVisibility(View.VISIBLE);
            }else{
                layoutDindingTerluas.setVisibility(View.GONE);
            }
        } else if(parent==b3_k5a){
            if(position<=6){
                layoutAtapTerluas.setVisibility(View.VISIBLE);
            }else{
                layoutAtapTerluas.setVisibility(View.GONE);
            }
        } else if(parent==b3_k9a){
            if(position==0){
                layoutSumberPeneranganUtama.setVisibility(View.VISIBLE);
            }else{
                layoutSumberPeneranganUtama.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public void onClick(View v) {
        if(v==btnTambahART){
            Intent it = new Intent(getContext(),AddActivity.class);
            it.putExtra("isEdit",false);
            it.putExtra("listART",listART);
            startActivityForResult(it,123);
        }else if(v==btnSimpan){
            progressDialog.show();
            String provinsi = String.format("%1$02d",b1_k1b);
            String kabupaten = String.format("%1$02d",b1_k2b);
            String kecamatan = String.format("%1$03d",Integer.parseInt(b1_k3b.getText().toString()));
            String desa = String.format("%1$03d",Integer.parseInt(b1_k4b.getText().toString()));
            String korong = String.format("%1$05d",Integer.parseInt(txtKodeSLS.getText().toString()));
            String nourut = String.format("%1$03d",Integer.parseInt(txtNoUrutRumahTangga.getText().toString()));
            String ruta_id = provinsi+kabupaten+kecamatan+desa+korong+nourut;

            Bundle bundle = new Bundle();
            DataFragment dataFragment = new DataFragment();
            Log.d("TAGG",utils.isOnline(getContext())+"");
            if(utils.isOnline(getContext())){
                try {
                    simpanPendudukOnline(ruta_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(),"Data berhasil disimpan secara ONLINE",Toast.LENGTH_LONG).show();
                bundle.putBoolean("isOnline",true);
            }else {
                simpanPendudukOffline(ruta_id);
                Toast.makeText(getActivity(),"Data berhasil disimpan secara OFFLINE",Toast.LENGTH_LONG).show();
                bundle.putBoolean("isOnline",false);
            }

            dataFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContent,dataFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else if(v==btnBatal){
            HomeFragment dataFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContent,dataFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    private void addToList(){
        if(listART.size()>0){
            listViewART.setVisibility(View.VISIBLE);
        }else{
            listViewART.setVisibility(View.GONE);
            return;
        }
        final DataARTAdapter dataARTAdapter = new DataARTAdapter(getActivity().getBaseContext(),listART);
        listViewART.setAdapter(dataARTAdapter);
        listViewART.setSelected(true);
        listViewART.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int selectionID = i;
                final CharSequence[] dialogitem = {"Edit", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent it = new Intent(getContext(),AddActivity.class);
                                it.putExtra("isEdit",true);
                                it.putExtra("listART",listART);
                                it.putExtra("position",selectionID);
                                startActivityForResult(it,123);
                                break;
                            case 1 :
                                listART.remove(selectionID);
                                Toast.makeText(getActivity(),"Data anggota rumah tangga berhasil dihapus.",Toast.LENGTH_LONG).show();
                                addToList();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewART.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123){
            if(resultCode == Activity.RESULT_OK){
                listART = (ArrayList<DataART>)data.getSerializableExtra("listART");
                Log.d("TAG",listART.size()+"");
                addToList();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        if(v==b5_k2a1){
            if(isChecked){
                layoutLahan.setVisibility(View.VISIBLE);
            }else{
                layoutLahan.setVisibility(View.GONE);
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

