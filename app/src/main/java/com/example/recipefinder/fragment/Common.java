package com.example.recipefinder.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinder.R;
import com.example.recipefinder.recipes.RecipeList;
import com.example.recipefinder.recipes.RecipeListViewDataAdapter;
import com.example.recipefinder.recipes.RecipeViewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Common {

    public static List<RecipeViewItem> getRecipesByTag(List<RecipeViewItem> totalRecipeList, String tagToFind) {

        List<RecipeViewItem> taggedRecipeList = new ArrayList<>();

        for (RecipeViewItem element : totalRecipeList) {
            String[] elementTags = element.getRecipeTags();
            if (elementTags != null) {
                List<String> tagList = Arrays.asList(elementTags);
                if (tagList.contains(tagToFind)) {
                    taggedRecipeList.add(element);
                }
            }
        }
        return taggedRecipeList;
    }

    public static void populateRecipeList(View view) {
        populateRecipeList(view, null);
    }

    /*
     * Fills out the recipe table and filters the full recipe list if a filter string is provided
     */
    public static void populateRecipeList(View view, String filter) {
        RecyclerView settingsRecyclerView = view.findViewById(R.id.all_recipes_list);

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
}
