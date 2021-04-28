
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

public class CarbonEmissionsAPI { // Class which funtions as an interface between Ilmastohelppi API and application
    Context context;
    private JSONObject jsonObject; // Request takes JSON object
    String resp;

    public CarbonEmissionsAPI(Context c){
        context = c;
    }

    public String sendRequest(int f){
        String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/PublicTransportEstimate";
        RequestQueue requestQueue = Volley.newRequestQueue(context); //Volley used to send the request
        jsonObject = new JSONObject();
        int g = 2*f; // Used to estimate saved emissions
        try {
            jsonObject.put("cityBusWeek",Integer.valueOf(f)); // Construction of JSON object
            jsonObject.put("cityTrainWeek",Integer.valueOf(f));// --.--
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonrequest = new JsonObjectRequest( // The Volley request itself
                Request.Method.GET,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response received",response.toString()); //Logging response

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Something happened",error.toString());

                    }
                }
        );

        requestQueue.add(jsonrequest); // Sends the actual response
        return resp; // Returns response back to application
    }
}

