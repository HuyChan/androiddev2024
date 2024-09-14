package vn.edu.usth.weather;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mviewPager;
    private BottomNavigationView bottomNavigationView;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }



    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("On stop");
    }

//        mviewPager = findViewById(R.id.viewpager);
////        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), getLifecycle());
//        mviewPager.setAdapter(adapter);
//
//        mviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
////            @Override
////            public void onPageSelected(int position) {
////                switch (position)
////                {
////                    case 0:
////                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
////                        break;
////                    case 1:
////                        bottomNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
////                        break;
////                    case 2:
////                        bottomNavigationView.getMenu().findItem(R.id.action_menu).setChecked(true);
////                        break;
////
////                }
////            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });
//
////        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                if (item.getItemId() == R.id.action_home) {
////                    mviewPager.setCurrentItem(0, true); // Switch to the first fragment
////                    return true;
////                }
////                if (item.getItemId() == R.id.action_search) {
////                    mviewPager.setCurrentItem(1, true); // Switch to the first fragment
////                    return true;
////                }
////                if (item.getItemId() == R.id.action_menu) {
////                    mviewPager.setCurrentItem(2, true); // Switch to the first fragment
////                    return true;
////                }
////
////                return false;
////            }
////        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });




}




