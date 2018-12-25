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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends Activity {

    public static final String AUTH_SESSION= "PERF_AUTH_SESSION";
    SharedPreferences sharedPreferences;
    EditText txtUsername,txtPassword;
    ProgressDialog pd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(getBaseContext());
        pd.setMessage("Loading...");

        sharedPreferences = getSharedPreferences(AUTH_SESSION,Context.MODE_PRIVATE);
        String token= sharedPreferences.getString("token",null);
        if(token == null){
            Intent it = new Intent(this, MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(it);
        }

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
                        int userid = 0;
                        String token = "";
                        Log.d("respone.login", response.toString());
                        try {
                            userid = response.getInt("userid");
                            token = response.getString("token");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        if (userid > 0) {
                            saveToken(userid, token);
                            txtUsername.setText("");
                            txtPassword.setText("");
                            Intent it = new Intent(getBaseContext(), MainActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(it);
                        } else {
                            Toast.makeText(getBaseContext(), "Username / Password salah",
                                    Toast.LENGTH_SHORT).show();
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
    public void saveToken(int uid, String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.putInt("uid",uid);
        editor.commit();
    }
}
