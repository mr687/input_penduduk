package com.penduduk.penduduk;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.print.PageRange;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.penduduk.penduduk.Utils.AUTH_SESSION;

public class DataFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    SharedPreferences sharedPreferences;
    ProgressDialog pd;
    List<Penduduk> penduduks;
    List<DataART> ARTs;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    DBHelper helper;
    boolean isOnline= false;
    boolean isScrolling = false;
    GridLayoutManager layoutManager;
    int currentItems, totalItems,scrollOutItems;
    ProgressBar progressBar;
    int currentItemPosition = 0;
    RelativeLayout relativeLayout;
    EditText search;
    String keywords;
    Button btnOK;

    private OnFragmentInteractionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        sharedPreferences = getActivity().getSharedPreferences(AUTH_SESSION,Context.MODE_PRIVATE);
        isOnline = this.getArguments().getBoolean("isOnline");
        relativeLayout = (RelativeLayout) view.findViewById(R.id.searchLayout);
        recyclerView  = (RecyclerView)view.findViewById(R.id.recyclerView);
        if(isOnline){
            relativeLayout.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
            params.setMargins(params.leftMargin,280,params.rightMargin,params.bottomMargin);
            recyclerView.setLayoutParams(params);
            getActivity().setTitle("Data Penduduk (Online)");
        }else{
            relativeLayout.setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
            params.setMargins(params.leftMargin,0,params.rightMargin,params.bottomMargin);
            recyclerView.setLayoutParams(params);
            getActivity().setTitle("Data Penduduk (Offline)");
        }
        keywords="";
        helper = new DBHelper(getContext());
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        search = (EditText) view.findViewById(R.id.search);
        btnOK  = (Button) view.findViewById(R.id.OK);
        layoutManager = new GridLayoutManager(getActivity().getBaseContext(),2);
        penduduks = new ArrayList<>();
        ARTs = new ArrayList<>();
        refreshData(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems)){
                    if(search.getText().toString().equals("")){
                        isScrolling = false;
                        refreshData(true);
                    }
                }
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penduduks = new ArrayList<>();
                ARTs = new ArrayList<>();
                currentItemPosition = 0;
                keywords = search.getText().toString();
                refreshData(false);
            }
        });
        return view;
    }

    public void refreshData(boolean isupdate){
        if(isOnline){
            getDataOnline(isupdate);
        }else{
            getDataOffline(helper.allPlayers());
        }
    }

    private void getDataOnline(final boolean isupdate){
        if(isupdate){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            pd = new ProgressDialog(getContext());
            pd.setMessage("Loading...");
            pd.show();
        }
        JsonArrayRequest request = new JsonArrayRequest(EndPoint.PENDUDUK_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Penduduk newPenduduk;
                        DataART newART;
                        if(!isupdate){
                            penduduks = new ArrayList<Penduduk>();
                            ARTs = new ArrayList<DataART>();
                        }
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject objRes = (JSONObject) response.get(i);
                                JSONArray arrART = (JSONArray) objRes.optJSONArray("artData");
                                for (int a=0;a<arrART.length();a++){
                                    JSONObject objART = (JSONObject) arrART.get(a);
                                    newART = new DataART(
                                            objART.optInt("b4_k1",0),
                                            objART.optString("b4_k2a",""),
                                            objART.optString("b4_k2b",""),
                                            objART.optInt("b4_k3",0),
                                            objART.optInt("b4_k4",0),
                                            objART.optInt("b4_k5",0),
                                            objART.optInt("b4_k6",0),
                                            objART.optInt("b4_k7",0),
                                            objART.optInt("b4_k8",0),
                                            objART.optInt("b4_k9",0),
                                            objART.optInt("b4_k10",0),
                                            objART.optInt("b4_k11",0),
                                            objART.optInt("b4_k12",0),
                                            objART.optInt("b4_k13",0),
                                            objART.optInt("b4_k14",0),
                                            objART.optInt("b4_k15",0),
                                            objART.optInt("b4_k16",0),
                                            objART.optInt("b4_k17",0),
                                            objART.optInt("b4_k18",0),
                                            objART.optInt("b4_k19a",0),
                                            objART.optString("b4_k19b",""),
                                            objART.optInt("b4_k20",0),
                                            objART.optInt("b4_k21",0)
                                    );
                                    ARTs.add(newART);
                                }
                                newPenduduk = new Penduduk(
                                        objRes.optString("id_ruta",""),
                                        objRes.optString("b1_k1a",""),
                                        objRes.optInt("b1_k1b",0),
                                        objRes.optString("b1_k2a",""),
                                        objRes.optInt("b1_k2b",0),
                                        objRes.optString("b1_k3a",""),
                                        objRes.optInt("b1_k3b",0),
                                        objRes.optString("b1_k4a",""),
                                        objRes.optInt("b1_k4b",0),
                                        objRes.optString("b1_k5",""),
                                        objRes.optString("b1_k6",""),
                                        objRes.optString("b1_k8",""),
                                        objRes.optInt("b1_k9",0),
                                        objRes.optInt("b1_k10",0),
                                        objRes.optInt("b3_k1a",0),
                                        objRes.optInt("b3_k1b",0),
                                        objRes.optString("b3_k2",""),
                                        objRes.optInt("b3_k3",0),
                                        objRes.optInt("b3_k4a",0),
                                        objRes.optInt("b3_k4b",0),
                                        objRes.optInt("b3_k5a",0),
                                        objRes.optInt("b3_k5b",0),
                                        objRes.optInt("b3_k6",0),
                                        objRes.optInt("b3_k7",0),
                                        objRes.optInt("b3_k8",0),
                                        objRes.optInt("b3_k9a",0),
                                        objRes.optInt("b3_k9b",0),
                                        objRes.optInt("b3_k10",0),
                                        objRes.optInt("b3_k11a",0),
                                        objRes.optInt("b3_k11b",0),
                                        objRes.optInt("b3_k12",0),
                                        objRes.optInt("b5_k1a",0),
                                        objRes.optInt("b5_k1b",0),
                                        objRes.optInt("b5_k1c",0),
                                        objRes.optInt("b5_k1d",0),
                                        objRes.optInt("b5_k1e",0),
                                        objRes.optInt("b5_k1f",0),
                                        objRes.optInt("b5_k1g",0),
                                        objRes.optInt("b5_k1h",0),
                                        objRes.optInt("b5_k1i",0),
                                        objRes.optInt("b5_k1j",0),
                                        objRes.optInt("b5_k1k",0),
                                        objRes.optInt("b5_k1l",0),
                                        objRes.optInt("b5_k1m",0),
                                        objRes.optInt("b5_k1n",0),
                                        objRes.optInt("b5_k1o",0),
                                        objRes.optInt("b5_k2a1",0),
                                        objRes.optInt("b5_k2a2",0),
                                        objRes.optInt("b5_k2b",0),
                                        objRes.optInt("b5_k3a",0),
                                        objRes.optInt("b5_k3b",0),
                                        objRes.optInt("b5_k3c",0),
                                        objRes.optInt("b5_k3d",0),
                                        objRes.optInt("b5_k3e",0),
                                        objRes.optInt("b5_k4a",0),
                                        objRes.optInt("b5_k5a",0),
                                        objRes.optInt("b5_k5b",0),
                                        objRes.optInt("b5_k5c",0),
                                        objRes.optInt("b5_k5d",0),
                                        objRes.optInt("b5_k5e",0),
                                        objRes.optInt("b5_k5f",0),
                                        objRes.optInt("b5_k5g",0),
                                        objRes.optInt("b5_k5h",0),
                                        objRes.optInt("b5_k5i",0),
                                        ARTs
                                        );
                                penduduks.add(newPenduduk);
                            }
                            if(isupdate){
                                adapter.notifyDataSetChanged();
                                currentItemPosition += 20;
                            }else{
                                adapter = new RecyclerViewAdapter(penduduks,getActivity().getBaseContext(),true);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                            }
                            if(isupdate){
                                progressBar.setVisibility(View.GONE);
                            }else{
                                pd.hide();
                            }
                        } catch (Exception ex) {
                            if(isupdate){
                                progressBar.setVisibility(View.GONE);
                            }else{
                                pd.hide();
                            }
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(isupdate){
                            progressBar.setVisibility(View.GONE);
                        }else{
                            pd.hide();
                        }
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                String token= sharedPreferences.getString("token",null);
                String nik= sharedPreferences.getString("uid",null);
                Map<String,String> params = new HashMap<String,String>();
                String limit = "20";
                String offset = currentItemPosition+"";
                params.put("uid",nik);
                params.put("token",token);
                params.put("limit",limit);
                params.put("offset",offset);
                params.put("keywords",keywords);
                return params;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity().getBaseContext());
        rq.add(request);
    }

    private void getDataOffline(List<Penduduk> list){
        adapter = new RecyclerViewAdapter(list,getContext(),false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

