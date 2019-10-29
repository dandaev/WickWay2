package com.example.wikway1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wikway1.ui.home.HomeFragment;
import com.example.wikway1.ui.saved.SavedFragment;
import com.example.wikway1.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
            bottomNav.setSelectedItemId(R.id.navigation_home);

        }

        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;
                        switch (menuItem.getItemId()){
                            case R.id.navigation_home :
                                selectedFragment = HomeFragment.getInstance();
                                break;
                            case R.id.navigation_saved :
                                selectedFragment = new SavedFragment();
                                break;
                            case R.id.navigation_search :
                                selectedFragment = new SearchFragment();
                                break;

                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,selectedFragment).commit();
                        return true;
                    }
                };
    }

