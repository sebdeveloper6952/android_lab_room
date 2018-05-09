package com.sebdeveloper6952.android_lab_room;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityA extends AppCompatActivity
{
    protected RequestQueue queue;
    protected final String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

    protected ListView lv_data;
    protected TextView txtV_response;
    private AppDatabase db;
    private List<String> weatherDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_activity_a);
        // lista para guardar data de weather a mostrar
        weatherDataList = new ArrayList<>();
        // list view
        lv_data = findViewById(R.id.lv_data);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ActivityA.this, android.R.layout.simple_list_item_1,
                weatherDataList
        );
        lv_data.setAdapter(adapter);
        txtV_response = findViewById(R.id.txtV_Title);
        // room database
        db = AppDatabase.getDatabase(ActivityA.this);
        // volley queue
        queue = Volley.newRequestQueue(ActivityA.this);

        JsonObjectRequest objRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            List<WeatherData> list = db.weatherDao().getWeatherDataById(1);
                            if(list.isEmpty())
                            {
                                WeatherData data = new WeatherData(1, response.toString());
                                db.weatherDao().insertWeatherData(data);
                                Toast.makeText(ActivityA.this, "Weather data added to db...",
                                        Toast.LENGTH_SHORT).show();
                            }
                            JSONObject obj = new JSONObject(list.get(0).getData()).getJSONObject("main");
                            weatherDataList.add("temp: " + obj.getString("temp"));
                            weatherDataList.add("pressure: " + obj.getString("pressure"));
                            weatherDataList.add("humidity: " + obj.getString("humidity"));
                            weatherDataList.add("temp_min: " + obj.getString("temp_min"));
                            weatherDataList.add("tem_max: " + obj.getString("temp_max"));
                            adapter.notifyDataSetChanged();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        System.out.println(error.getMessage());
                    }
                });
        queue.add(objRequest);


    }

    @Override
    protected void onDestroy()
    {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
