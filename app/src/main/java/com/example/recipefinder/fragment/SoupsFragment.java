package com.example.recipefinder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinder.R;
import com.example.recipefinder.recipes.RecipeList;
import com.example.recipefinder.recipes.RecipeListViewDataAdapter;
import com.example.recipefinder.recipes.RecipeViewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SoupsFragment extends Fragment {

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_soups, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView settingsRecyclerView = view.findViewById(R.id.soup_recipe_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        settingsRecyclerView.setLayoutManager(gridLayoutManager);


        List<RecipeViewItem> totalRecipeList = RecipeList.getRecipeList();
        List<RecipeViewItem> soupRecipeList = new ArrayList<>();;

        for (RecipeViewItem element : totalRecipeList) {
            String[] elementTags =  element.getRecipeTags();
            List<String> tagList = Arrays.asList(elementTags);
            if (tagList.contains("Soup")) {
                soupRecipeList.add(element);
            }
        }

        RecipeListViewDataAdapter recipeDataAdapter = new RecipeListViewDataAdapter(soupRecipeList);
        settingsRecyclerView.setAdapter(recipeDataAdapter);
    }
}