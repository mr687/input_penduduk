package com.penduduk.penduduk;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String AUTH_SESSION= "PERF_AUTH_SESSION";
    public boolean status = false;
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
    public void postData(String url, final HashMap data, final Context mContext) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("status")==1){
                                Toast.makeText(mContext,"Data berhasil di simpan di database.",Toast.LENGTH_LONG).show();
                                DBHelper helper = new DBHelper(mContext);
                                long resp = helper.deletePlayer(response.getString("id_ruta"));
                                if(resp == 1){
                                    DataFragment dataFragment = new DataFragment();
                                    dataFragment.refreshData();
                                }
                            }else if(response.getInt("status") == 2){
                                Toast.makeText(mContext,"Data gagal di simpan di database. ID Ruta " + response.getString("id_ruta") + " sudah ada.",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAGG","Error message : "+error.getMessage());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(mContext);
        rq.add(jsonObjectRequest);
    }
}
