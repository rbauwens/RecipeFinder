package com.example.recipefinder.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.recipefinder.R;
import com.example.recipefinder.fragment.BrunchFragment;
import com.example.recipefinder.fragment.HomeFragment;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Fragment savedFragment;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "myFragmentName", savedFragment);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedFragment = null;

        if (savedInstanceState != null) {
            savedFragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
        }
        setContentView(R.layout.activity_main);

        if (savedFragment != null) {
            loadFragment(savedFragment);
        } else {
            int intentFragment = R.id.nav_home;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                intentFragment = extras.getInt("fragmentToLoad");
            }
            loadFragment(intentFragment);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);
        navigationView = findViewById(R.id.nav_view);

        // initialize navigation menu
        setupDrawerContent();



    }

    private void loadFragment(Fragment fragmentToLoad) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragmentToLoad).commit();
        savedFragment = fragmentToLoad;
    }

    private void loadFragment(int fragment) {
        Class fragmentClass;
        String title;
        Fragment fragmentToLoad = null;

        switch (fragment) {
            case R.id.nav_home:
//                title = getString(R.string.nav_home);
                title = "Home";
                fragmentClass = HomeFragment.class;
                break;
            default:
                title = "Home";
                fragmentClass = HomeFragment.class;
        }

        try {
            fragmentToLoad = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragmentToLoad != null) {
            loadFragment(fragmentToLoad);
            setTitle(title);
        }

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged( Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private void setupDrawerContent() {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        loadFragment(menuItem.getItemId());
        menuItem.setChecked(true);
        drawer.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchBrunchFragment(View view) {
        loadFragment(new BrunchFragment());
        setTitle("Brunch Recipes");
    }
}