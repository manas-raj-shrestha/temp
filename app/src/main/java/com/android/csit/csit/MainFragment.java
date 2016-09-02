package com.android.csit.csit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment {
    private static final String FILENAME = "csit_prefs";
    TextView tvUserName, tvEmail;

    public MainFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUserName = (TextView) view.findViewById(R.id.username);
        tvUserName.setText(getSavedUserName());

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        makeNetworkRequest();
    }

    private String getSavedUserName() {
        SharedPreferences prefs = getContext().getApplicationContext().getSharedPreferences(FILENAME, getContext().MODE_PRIVATE);
        return prefs.getString("username", "NA");
    }

    private void makeNetworkRequest() {
        Log.e("stated", "started");
        String url = "http://192.168.1.15:80/client/product.php";
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<ResponseModel> responseModels = new ArrayList<>();
                        Log.e("response", " " + response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                try {
                                    ResponseModel responseModel = new ResponseModel();
                                    responseModel.id = jsonObject.getString("id");
                                    responseModel.email = jsonObject.getString("email");
                                    responseModel.username = jsonObject.getString("username");
                                    responseModel.phone = jsonObject.getString("phone");
                                    responseModel.college = jsonObject.getString("college");
                                    responseModel.semester = jsonObject.getString("semester");

                                    responseModels.add(responseModel);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyManager.getInstance(getContext().getApplicationContext()).addToRequestQueue(getRequest);
    }
}
