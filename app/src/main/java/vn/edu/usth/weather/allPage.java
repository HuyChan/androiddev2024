package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class allPage extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView usthLogoImageView;
    private TextView weatherTempTextView;
    private TextView weatherDescriptionTextView;

    // Replace with your actual OpenWeather API key
    private final String API_KEY = "YOUR_API_KEY";
    private final String CITY = "Hanoi";
    private final String UNITS = "metric"; // Use "imperial" for Fahrenheit

    public allPage() {
        // Required empty public constructor
    }

    public static allPage newInstance(String param1, String param2) {
        allPage fragment = new allPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_page, container, false);
        usthLogoImageView = view.findViewById(R.id.Iamgeview);
        weatherTempTextView = view.findViewById(R.id.weatherTempTextView);
        weatherDescriptionTextView = view.findViewById(R.id.weatherDescriptionTextView);

        // Sử dụng parseWeatherData() thay vì fetchWeatherData() để đọc từ file JSON giả lập
        parseWeatherData();



        // Start the network request to download the USTH logo
//        downloadUSTHLogo("https://via.placeholder.com/300.png");

        // Fetch weather data

        return view;
    }

//    private void downloadUSTHLogo(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
//        ImageRequest imageRequest = new ImageRequest(
//                url,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        usthLogoImageView.setImageBitmap(response);
//                        Toast.makeText(getContext(), "Logo downloaded successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Failed to download logo.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        requestQueue.add(imageRequest);
//    }

//    private void fetchWeatherData() {
//        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY +
//                "&units=" + UNITS + "&appid=" + API_KEY;
//
//        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            // Parse the JSON data
//                            JSONObject main = response.getJSONObject("main");
//                            double temp = main.getDouble("temp");
//                            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
//                            String description = weather.getString("description");
//
//                            // Update UI with the parsed data
//                            weatherTempTextView.setText("Temperature: " + temp + "°C");
//                            weatherDescriptionTextView.setText("Condition: " + description);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getContext(), "JSON parsing error.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), "Failed to get weather data.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//        requestQueue.add(jsonObjectRequest);
//    }

    private JSONObject loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("weather_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            Log.d("JSON_CONTENT", json); // Kiểm tra nội dung JSON
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void parseWeatherData() {
        JSONObject response = loadJSONFromAsset();
        if (response != null) {
            try {
                JSONArray dataArray = response.getJSONArray("data");

                // Chọn bộ dữ liệu đầu tiên thay vì lặp qua toàn bộ mảng
                JSONObject weatherData = dataArray.getJSONObject(0);

                // Đọc nhiệt độ từ "main"
                JSONObject main = weatherData.getJSONObject("main");
                double temp = main.getDouble("temp");

                // Đọc mô tả thời tiết từ "weather"
                JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
                String description = weather.getString("description");

                // Hiển thị nhiệt độ và điều kiện thời tiết
                weatherTempTextView.setText("Temperature: " + temp + "°C");
                weatherDescriptionTextView.setText("Condition: " + description);

                Log.d("WEATHER_DATA", "Temperature: " + temp + ", Condition: " + description);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "JSON parsing error.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Failed to load JSON data.", Toast.LENGTH_SHORT).show();
        }
    }

}
