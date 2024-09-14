package vn.edu.usth.weather;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;


import vn.edu.usth.weather.ViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;


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

        // set view pager
        FragmentManager fm = getSupportFragmentManager();
        ViewAdapter pagerAdapter = new ViewAdapter(fm, getLifecycle());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setCurrentItem(0);

        // tab layout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Hanoi, Viet Nam"));
        tabLayout.addTab(tabLayout.newTab().setText("Paris, France"));

        // connecting tab layout to adapter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        // set linear layout
//        LinearLayout linearLayout = new LinearLayout(getBaseContext());
//        View forecast_view = findViewById(R.id.forecast_fragment_container);
//        linearLayout.addView(forecast_view);

//        ForecastFragment forecastFragment = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.forecast_fragment_container, forecastFragment)
//                .commit();
//        setContentView(R.layout.background);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("start", "On start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "On pause");
    }



    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "On stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "On destroy");
    }
}
