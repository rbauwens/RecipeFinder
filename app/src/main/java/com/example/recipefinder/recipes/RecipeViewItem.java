package com.example.recipefinder.recipes;

public class RecipeViewItem {

    // Save photo name.
    private String recipeName;

    // Save image resource id.
    private int recipeImageId;

    private String recipeFilePath;

    public RecipeViewItem(String recipeName, int recipeImageId) {
        this.recipeName = recipeName;
        this.recipeImageId = recipeImageId;
    }

    public RecipeViewItem(String recipeName, String recipeFilePath) {
        this.recipeName = recipeName;
        this.recipeImageId = 0;
        this.recipeFilePath = recipeFilePath;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getRecipeImageId() {
        return recipeImageId;
    }

    public String getRecipeFilePath() {
        return recipeFilePath;
    }
}
