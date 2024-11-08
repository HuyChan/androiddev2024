package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link allPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class allPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imageView;


    public allPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment allPage.
     */
    // TODO: Rename and change types and number of parameters
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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_all_page, container, false);
//        imageView = view.findViewById(R.id.Iamgeview);
//        new ImageDownloadTask().execute();
//        return view;
//    }
//
//    private class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(Void... voids) {
//            Bitmap bitmap = null;
//            try {
//                URL url = new URL("https://cdn.haitrieu.com/wp-content/uploads/2022/11/Logo-Truong-Dai-hoc-Khoa-hoc-va-Cong-nghe-Ha-Noi.png");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                InputStream inputStream = connection.getInputStream();
//                bitmap = BitmapFactory.decodeStream(inputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            if (bitmap != null) {
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }

    private ImageView usthLogoImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_page, container, false);
        usthLogoImageView = view.findViewById(R.id.Iamgeview);
        // Start the network request to download the USTH logo
        downloadUSTHLogo("https://www.usth.edu.vn/uploads/logo.png");
        return view;
    }
    private void downloadUSTHLogo(String url) {
        // Get the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        // Create an ImageRequest to download the image
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Set the downloaded image in the ImageView
                        usthLogoImageView.setImageBitmap(response);
                        Toast.makeText(getContext(), "Logo downloaded successfully!", Toast.LENGTH_SHORT).show();
                    }
                },
                0, // Width (0 means use original size)
                0, // Height (0 means use original size)
                ImageView.ScaleType.CENTER, // Image scale type
                Bitmap.Config.RGB_565, // Bitmap configuration
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(getContext(), "Failed to download logo.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // Add the request to the request queue
        requestQueue.add(imageRequest);
    }
}