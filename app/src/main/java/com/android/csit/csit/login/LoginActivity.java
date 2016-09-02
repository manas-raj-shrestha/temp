package com.android.csit.csit.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.csit.csit.R;
import com.android.csit.csit.VolleyManager;
import com.android.csit.csit.dashboard.DashboardActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String FILENAME = "csit_prefs";

    EditText edtPassword;
    EditText edtUsername;
    Button btnLogin;
    CheckBox cbRememberCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        cbRememberCredentials = (CheckBox) findViewById(R.id.cb_remember_credentials);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNetworkRequest();
            }
        });

        initializeEditTexts();
    }

    private void initializeEditTexts() {
        if (!getSavedPassword().contentEquals("NA")) {
            edtUsername.setText(getSavedUserName());
            edtPassword.setText(getSavedPassword());
        }
    }

    private void makeNetworkRequest() {
        String url = "http://192.168.1.15:80/client/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);

                        if (response.contains("success")) {
                            Intent in = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(in);
                            if (cbRememberCredentials.isChecked())
                                saveToPrefs(edtUsername.getText().toString(), edtPassword.getText().toString());

                            Toast.makeText(getApplicationContext(), "Right Username or Password", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "Time Out Error", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof com.android.volley.NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof com.android.volley.ServerError) {
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof com.android.volley.ParseError) {
                    Toast.makeText(getApplicationContext(), "JSON Parse Error", Toast.LENGTH_SHORT).show();
                }

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("txtUsername", edtUsername.getText().toString());
                params.put("txtPassword", edtPassword.getText().toString());

                return params;
            }

        };
        VolleyManager.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    /**
     * save username and password
     *
     * @param userName username
     * @param password password
     */
    private void saveToPrefs(String userName, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(FILENAME, MODE_PRIVATE).edit();
        editor.putString("username", userName);
        editor.putString("password", password);
        editor.commit();
    }

    private String getSavedPassword() {
        SharedPreferences prefs = getSharedPreferences(FILENAME, MODE_PRIVATE);
        return prefs.getString("password", "NA");
    }

    private String getSavedUserName() {
        SharedPreferences prefs = getSharedPreferences(FILENAME, MODE_PRIVATE);
        return prefs.getString("username", "NA");
    }


}
