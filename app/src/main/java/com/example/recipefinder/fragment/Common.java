package com.example.recipefinder.fragment;

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
}
