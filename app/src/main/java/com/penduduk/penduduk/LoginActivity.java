package com.penduduk.penduduk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.penduduk.penduduk.Utils.AUTH_SESSION;

public class LoginActivity extends Activity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText txtUsername,txtPassword;
    ProgressDialog pd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(AUTH_SESSION,Context.MODE_PRIVATE);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        checkAuth();

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button)  findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        txtUsername.setText("");
        txtPassword.setText("");
    }
    private void checkAuth(){
        pd.show();
        HashMap<String,String> param = new HashMap<>();
        param.put("token",sharedPreferences.getString("token",null));
        param.put("uid",sharedPreferences.getString("uid",null));
        JsonObjectRequest request =new JsonObjectRequest(EndPoint.CHECKAUTH_URL, new JSONObject(param),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String message="";
                        try {
                            message = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pd.hide();
                        if(message.equals("authorized")){
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                        }else{
                            editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.hide();
                error.printStackTrace();
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(request);
    }
    public void sendRequest(){
        pd.show();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        HashMap<String, String> param = new HashMap<>();
        param.put("username",username);
        param.put("password",password);
        JsonObjectRequest request =new JsonObjectRequest(EndPoint.LOGIN_URL, new JSONObject(param),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.hide();
                        String userid = "";
                        String token = "";
                        String role = "";
                        String kecamatan = "";
                        String desa = "";
                        Log.d("respone.login", response.toString());
                        try {
                            role = response.getString("Role");
                            kecamatan = response.getString("Kecamatan");
                            desa = response.getString("Desa");
                            userid = response.getString("Nik");
                            token = response.getString("token");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        if(role.equals("2") || role.equals("1")){
                            Toast.makeText(getBaseContext(), "Akses hanya diperbolehka untuk petugas lapangan.",
                                    Toast.LENGTH_SHORT).show();
                            txtUsername.setText("");
                            txtPassword.setText("");
                            return;
                        }

                        if (userid != "") {
                            saveToken(userid, token,role,kecamatan,desa);
                            txtUsername.setText("");
                            txtPassword.setText("");
                            Intent it = new Intent(getBaseContext(), MainActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                        } else {
                            Toast.makeText(getBaseContext(), "Username / Password salah",
                                    Toast.LENGTH_SHORT).show();
                            txtUsername.setText("");
                            txtPassword.setText("");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.hide();
                Log.d("json.response", error.getMessage());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(request);
    }
    public void saveToken(String uid, String token,String role,String kecamatan,String desa){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.putString("uid",uid);
        editor.putString("role",role);
        editor.putString("kecamatan",kecamatan);
        editor.putString("desa",desa);
        editor.commit();
    }
}
