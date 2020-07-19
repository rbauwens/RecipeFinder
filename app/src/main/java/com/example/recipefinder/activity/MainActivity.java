package com.example.recipefinder.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.recipefinder.R;
import com.example.recipefinder.fragment.AddRecipeFragment;
import com.example.recipefinder.fragment.Common;
import com.example.recipefinder.fragment.HomeFragment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.recipefinder.recipes.RecipeList;
import com.example.recipefinder.recipes.RecipeListViewDataAdapter;
import com.example.recipefinder.recipes.RecipeViewItem;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

        RecipeList.initialiseList(this.getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
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

        if (fragment == R.id.nav_home) {
            title = getString(R.string.app_title);
            fragmentClass = HomeFragment.class;
        } else {
            title = getString(R.string.app_title);
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
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // selecting items from the side drawer menu
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // selecting items from the on screen buttons
        if (item.getItemId() == R.id.action_add) {// toolbar add recipe button
            loadFragment(new AddRecipeFragment());
            setTitle("Add New Recipe");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateRecipeList(View view) {
        populateRecipeList(view, null);
    }

    /*
     * Fills out the recipe table and filters the full recipe list if a filter string is provided
     */
    private void populateRecipeList(View view, String filter) {
        View parentView = (View) view.getParent();
        RecyclerView settingsRecyclerView = parentView.findViewById(R.id.all_recipes_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        settingsRecyclerView.setLayoutManager(gridLayoutManager);

        List<RecipeViewItem> totalRecipeList = RecipeList.getRecipeList();
        RecipeListViewDataAdapter recipeDataAdapter;
        if (filter != null) {
            List<RecipeViewItem> filteredRecipeList = Common.getRecipesByTag(totalRecipeList, filter);
            recipeDataAdapter = new RecipeListViewDataAdapter(filteredRecipeList);
        } else {
            recipeDataAdapter = new RecipeListViewDataAdapter(totalRecipeList);
        }

        settingsRecyclerView.setAdapter(recipeDataAdapter);
    }

    public void launchAllRecipesFragment(View view) {
        populateRecipeList(view);
    }

    public void launchBrunchFragment(View view) {
        populateRecipeList(view, "Brunch");
    }

    public void launchSoupsFragment(View view) {
        populateRecipeList(view, "Soup");
    }

    public void launchDrinksFragment(View view) {
        populateRecipeList(view, "Drinks");
    }

    public void launchLunchFragment(View view) {
        populateRecipeList(view, "Lunch");
    }

    public void launchDinnerFragment(View view) {
        populateRecipeList(view, "Dinner");
    }

    public void launchDessertFragment(View view) {
        populateRecipeList(view, "Dessert");
    }

    public void launchBakingFragment(View view) {
        populateRecipeList(view, "Baking");
    }

}
