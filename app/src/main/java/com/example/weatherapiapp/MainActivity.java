package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    Button btn_cityID,btn_GetWeatherByID,btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   assign values to each control on the layout
        btn_cityID =findViewById(R.id.btn_getCityID);
        btn_GetWeatherByID =findViewById(R.id.btn_getWeatherBYCityID);
        btn_getWeatherByName =findViewById(R.id.btn_getWeatherBYCityName);

        et_dataInput =findViewById(R.id.et_dataInput);
        lv_weatherReport =findViewById(R.id.lv_weatherReport);
        // click listener for each button
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                String city= et_dataInput.getText().toString();
                String url ="https://api.postalpincode.in/postoffice/"+et_dataInput.getText().toString();

               JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
//                       String Temp = "";
//                       JSONObject object =null;
//                       JSONArray post ;
//                       JSONObject pin = null;
                       try {
                          JSONObject  object = response.getJSONObject(0);
                          JSONArray post = object.getJSONArray("PostOffice");
                          JSONObject pin = post.getJSONObject(0);
                          String Temp = pin.getString("Pincode");
                           Toast.makeText(MainActivity.this, "The City PIN CODE is "+Temp , Toast.LENGTH_SHORT).show();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(MainActivity.this, "Please Enter Valid City", Toast.LENGTH_SHORT).show();
                   }
               });

                queue.add(request);


                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
//                    }
//                });
                // Add the request to the RequestQueue.





              //  Toast.makeText(MainActivity.this, "button1", Toast.LENGTH_SHORT).show();
            }
        });

        btn_GetWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String id= et_dataInput.getText().toString();
                String url ="https://api.openweathermap.org/geo/1.0/zip?zip="+id+",IN&appid=398648dd804da0a84703fb8da9f1cffd";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        String Temp = "";
                        try {
                            JSONObject object = response.getJSONObject("name");
//                          Temp = object.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Toast.makeText(MainActivity.this, "The City is "+response.getString("name") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Please Enter Valid Zip Code", Toast.LENGTH_SHORT).show();
                    }
                });










//                JsonArrayRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject object = response.getJSONObject(0);
//                            String Temp= object.getString("name");
//                            double temp=Double.parseDouble(Temp)-273.15;
//                            Toast.makeText(MainActivity.this, "the city is " + Temp, Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Please Enter Valid City", Toast.LENGTH_SHORT).show();
//                    }
//                });

                queue.add(request);







               // Toast.makeText(MainActivity.this, "button2", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String city= et_dataInput.getText().toString();
                String url ="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=398648dd804da0a84703fb8da9f1cffd";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("main");
                            String Temp= object.getString("temp");
                            double temp=Double.parseDouble(Temp)-273.15;
                            Toast.makeText(MainActivity.this, "The Temperature is "+ Double.toString(temp).substring(0,7)+" \u2103", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Please Enter Valid City", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);





                //Toast.makeText(MainActivity.this, "You Typed"+ et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}