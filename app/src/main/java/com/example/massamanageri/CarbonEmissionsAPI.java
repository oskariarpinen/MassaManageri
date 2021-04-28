package com.example.massamanageri;

import android.app.VoiceInteractor;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class CarbonEmissionsAPI {
    Context context;
    private JSONObject jsonObject;
    String resp;

    public CarbonEmissionsAPI(Context c){
        context = c;
    }

    public String sendRequest(int f){
        String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/PublicTransportEstimate";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        jsonObject = new JSONObject();
        int g = 2*f;
        try {
            jsonObject.put("Train",Integer.valueOf(f));
            jsonObject.put("Bus",Integer.valueOf(f));
            jsonObject.put("Other",Integer.valueOf(0));
            jsonObject.put("Total",Integer.valueOf(g));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonrequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response received",response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Something happened",error.toString());

                    }
                }
        );

        requestQueue.add(jsonrequest);
        return resp;
    }
}

