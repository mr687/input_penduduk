package com.penduduk.penduduk;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity
implements CompoundButton.OnCheckedChangeListener,View.OnClickListener,AdapterView.OnItemSelectedListener {
    private List<DataART>  listART;
    private EditText txtNama,txtNIK,txtNoUrutKeluarga,txtUmur,txtKepemilikanKartuIdentitas,txtJumlahJamKerja,txtB4_k1;
    private Spinner spinnerHubKRT,spinnerHubKel,spinnerJenisKelamin,spinnerStatusPerkawinan,spinnerKepemilikanAktaNikah,
    spinnerJenisCacat,spinnerPenyakitKronis,spinnerPartisipasiSekolah,spinnerPendidikanTertinggi,spinnerKelasTertinggi,
    spinnerIjazahTertinggi,spinnerLapanganUsaha,spinnerStatusDalamKerja;
    private CheckBox checkTercantumKK,checkStatusKehamilan,checkBekerjaSemingguLalu;
    private Button btnSimpan,btnBatal;
    private boolean isEdit;
    private int position;
    private LinearLayout layoutKol15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Tambah Anggota Rumah Tangga");

        btnBatal = (Button) findViewById(R.id.btnBatal);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        checkBekerjaSemingguLalu = (CheckBox) findViewById(R.id.checkBekerjaMingguLalu);
        checkTercantumKK = (CheckBox) findViewById(R.id.checkTercantumDalamKK);
        checkStatusKehamilan = (CheckBox) findViewById(R.id.checkStatusKehamilan);


        txtNama = (EditText)  findViewById(R.id.txtNama);
        txtNIK =(EditText) findViewById(R.id.txtNIK);
        txtNoUrutKeluarga = (EditText) findViewById(R.id.nob4_k1);
        txtUmur = (EditText) findViewById(R.id.txtUmur);
        txtKepemilikanKartuIdentitas = (EditText) findViewById(R.id.txtKepemilikanIdentitas);
        txtJumlahJamKerja = (EditText) findViewById(R.id.txtJumlahLamaKerja);

        spinnerHubKRT = (Spinner) findViewById(R.id.spinnerHubuganDenganKRT);
        spinnerHubKel = (Spinner) findViewById(R.id.spinnerHubuganDenganKK);
        spinnerJenisKelamin = (Spinner) findViewById(R.id.spinnerJenisKelamin);
        spinnerStatusPerkawinan = (Spinner) findViewById(R.id.spinnerStatusPerkawinan);
        spinnerKepemilikanAktaNikah = (Spinner) findViewById(R.id.spinnerKepemilikanAktaNikah);
        spinnerJenisCacat = (Spinner) findViewById(R.id.spinnerJenisCacat);
        spinnerPenyakitKronis = (Spinner) findViewById(R.id.spinnerPenyakitKronis);
        spinnerPartisipasiSekolah = (Spinner) findViewById(R.id.spinnerPartisipasiSekolah);
        spinnerPendidikanTertinggi = (Spinner) findViewById(R.id.spinnerJenjangPendidikanTertinggi);
        spinnerKelasTertinggi = (Spinner) findViewById(R.id.spinnerKelasTertinggi);
        spinnerIjazahTertinggi = (Spinner) findViewById(R.id.spinnerIjazahTertinggi);
        spinnerLapanganUsaha = (Spinner) findViewById(R.id.spinnerLapanganUsaha);
        spinnerStatusDalamKerja = (Spinner) findViewById(R.id.spinnerStatusKedudukan);

        layoutKol15 = (LinearLayout) findViewById(R.id.layoutKol15);

        spinnerPartisipasiSekolah.setOnItemSelectedListener(this);

        listART = (ArrayList<DataART>) getIntent().getSerializableExtra("listART");
        position = getIntent().getIntExtra("position",0);

        checkBekerjaSemingguLalu.setOnCheckedChangeListener(this);
        btnBatal.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
        isEdit = getIntent().getBooleanExtra("isEdit",false);
        if(isEdit){
            populateART(listART.get(position));
        }
    }
    private void populateART(DataART data){
        try{
//            txtB4_k1.setText(data.b4_k1);
            txtNama.setText(data.b4_k2a);
            txtNIK.setText(data.b4_k2b);
            spinnerHubKRT.setSelection(Integer.valueOf(data.b4_k3)-1);
            txtNoUrutKeluarga.setText(String.valueOf(data.b4_k4));
            spinnerHubKel.setSelection(Integer.valueOf(data.b4_k5)-1);
            spinnerJenisKelamin.setSelection(Integer.valueOf(data.b4_k6)-1);
            txtUmur.setText(data.b4_k7+"");
            spinnerStatusPerkawinan.setSelection(Integer.valueOf(data.b4_k8)-1);
            spinnerKepemilikanAktaNikah.setSelection(Integer.valueOf(data.b4_k9)-1);
            checkTercantumKK.setChecked((data.b4_k10==1));
            String dataKepemilikianKartuIdentitas = String.valueOf(data.b4_k11+"");
            txtKepemilikanKartuIdentitas.setText(dataKepemilikianKartuIdentitas);
            checkStatusKehamilan.setChecked((data.b4_k12==1));
            spinnerJenisCacat.setSelection(Integer.valueOf(data.b4_k13)-1);
            spinnerPenyakitKronis.setSelection(Integer.valueOf(data.b4_k14)-1);
            spinnerPartisipasiSekolah.setSelection(Integer.valueOf(data.b4_k15)-1);
            spinnerPendidikanTertinggi.setSelection(Integer.valueOf(data.b4_k16)-1);
            spinnerKelasTertinggi.setSelection(Integer.valueOf(data.b4_k17)-1);
            spinnerIjazahTertinggi.setSelection(Integer.valueOf(data.b4_k18)-1);
            checkBekerjaSemingguLalu.setChecked((data.b4_k19a==1));
            txtJumlahJamKerja.setText(data.b4_k19b+"");
            spinnerLapanganUsaha.setSelection(Integer.valueOf(data.b4_k20)-1);
            spinnerStatusDalamKerja.setSelection(Integer.valueOf(data.b4_k21)-1);
        }catch (Exception ex){
            Log.e("TAGG",ex.toString());
        }
    }
    private boolean addART(){
        try{
            String kepemilikanIdentitas = txtKepemilikanKartuIdentitas.getText().toString();
            DataART newDataART = new DataART(
                    Integer.valueOf(0),
                    txtNama.getText().toString(),
                    txtNIK.getText().toString(),
                    (int)(spinnerHubKRT.getSelectedItemId() + 1),
                    (txtNoUrutKeluarga.getText().toString().equals(""))? 0: Integer.parseInt(txtNoUrutKeluarga.getText().toString()),
                    (int)(spinnerHubKel.getSelectedItemId()+1),
                    (int)(spinnerJenisKelamin.getSelectedItemId()+1),
                    (txtUmur.getText().toString().equals(""))?0:Integer.parseInt(txtUmur.getText().toString()),
                    (int)(spinnerStatusPerkawinan.getSelectedItemId()+1),
                    (int)(spinnerKepemilikanAktaNikah.getSelectedItemId()+1),
                    (checkTercantumKK.isChecked() ? 1:0),
                    (kepemilikanIdentitas.equals("")?0:Integer.parseInt(kepemilikanIdentitas)),
                    (checkStatusKehamilan.isChecked()?1:0),
                    (int)(spinnerJenisCacat.getSelectedItemId()+1),
                    (int)(spinnerPenyakitKronis.getSelectedItemId()+1),
                    (int)(spinnerPartisipasiSekolah.getSelectedItemId()+1),
                    (int)(spinnerPendidikanTertinggi.getSelectedItemId()+1),
                    (int)(spinnerKelasTertinggi.getSelectedItemId()+1),
                    (int)(spinnerIjazahTertinggi.getSelectedItemId()+1),
                    (checkBekerjaSemingguLalu.isChecked() ? 1 : 0),
                    (checkBekerjaSemingguLalu.isChecked() ? txtJumlahJamKerja.getText().toString() : "0"),
                    (int)(spinnerLapanganUsaha.getSelectedItemId()+1),
                    (int)(spinnerStatusDalamKerja.getSelectedItemId()+1)
            );
            if(isEdit){
                listART.set(position,newDataART);
            }else{
                listART.add(newDataART);
            }
            return true;
        }catch (Exception ex){
            Log.e("TAGG","eRROR: "+ex.getMessage());
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        if(v==checkBekerjaSemingguLalu){
            if(isChecked){
                txtJumlahJamKerja.setVisibility(View.VISIBLE);
            }else{
                txtJumlahJamKerja.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btnBatal){
            finish();
        }else if(v==btnSimpan){
            addART();
            Toast.makeText(getBaseContext(),"Data anggota rumah tangga berhasil disimpan.",Toast.LENGTH_LONG).show();
            Intent it = new Intent();
            it.putExtra("listART",(ArrayList<DataART>)listART);
            setResult(Activity.RESULT_OK,it);
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent==spinnerPartisipasiSekolah){
            if(position==0){
                layoutKol15.setVisibility(View.GONE);
            }else{
                layoutKol15.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
