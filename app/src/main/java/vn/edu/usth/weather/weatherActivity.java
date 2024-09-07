package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class weatherActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.cloudFragment , new CloudFragment())
                .replace(R.id.fragment_forecast_frangment, new ForecastFrangment())
                .commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("==Start==", "==onStart==");
    }

    protected void onResume(){
        super.onResume();
        Log.i("==Resume==", "==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("==Pause==", "==onPause==");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("==Restart==", "==onRestart==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("==Stop==", "==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("==Destroy==", "==onDestroy==");
    }

}