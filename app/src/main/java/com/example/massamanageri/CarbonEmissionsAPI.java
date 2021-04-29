
package com.example.massamanageri;

import android.app.VoiceInteractor;
import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
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
    String urlEnd, url;

    public CarbonEmissionsAPI(Context c) {
        context = c;
    }

    public void sendRequest(int f) {
        int kilometers = f/60;
        kilometers = kilometers*5;
        RequestQueue requestQueue = Volley.newRequestQueue(context); //Volley used to send the request
        int g = 2 * f; // Used to estimate saved emissions
        String defaultURL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/PublicTransportEstimate?";
        urlEnd = "cityBusWeek=" + Integer.valueOf(kilometers) + "&cityTrainWeek=" + Integer.valueOf(0);
        url = defaultURL + urlEnd;

        JsonObjectRequest jsonrequest = new JsonObjectRequest( // The Volley request itself
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObj = null;
                        try {
                            jsonObj = new JSONObject(String.valueOf(response));
                            String response_value = jsonObj.getString("Total");
                            FileReadAndWrite io = new FileReadAndWrite();
                            io.writeapiresponse(response_value, context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Response received", response.toString()); //Logging response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Something happened", error.toString());

                    }
                }
        );
        requestQueue.add(jsonrequest); // Sends the actual response
    }
}


