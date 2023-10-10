package com.example.botomnavigationviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnView;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView = findViewById(R.id.bnView);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toll_bar);
        navView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.is_open, R.string.is_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.clean) {
                    Toast.makeText(MainActivity.this, "Clean arrow clicked", Toast.LENGTH_SHORT).show();
                    replaceFragment(new HomeFragment(), true);
                } else if (id == R.id.right) {
                    replaceFragment(new ContractFragment(), true);
                    Toast.makeText(MainActivity.this, "Right arrow clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.left) {
                    Toast.makeText(MainActivity.this, "Left arrow clicked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thumps arrow clicked", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    replaceFragment(new HomeFragment(), false);
                } else if (id == R.id.nav_Contract) {
                    replaceFragment(new ContractFragment(), true);
                } else if (id == R.id.nav_Search) {
                    replaceFragment(new SearchFragment(), true);
                } else if (id == R.id.nav_utilities) {
                    replaceFragment(new UtilitesFragment(), true);
                } else {
                    replaceFragment(new ProfileFragment(), true);
                }
                return true;
            }
        });
        bnView.setSelectedItemId(R.id.nav_home);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void replaceFragment(Fragment fragment, boolean isReplace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (isReplace)
            ft.replace(R.id.frame_container, fragment);
        else
            ft.add(R.id.frame_container, fragment);

        ft.commit();
    }

}