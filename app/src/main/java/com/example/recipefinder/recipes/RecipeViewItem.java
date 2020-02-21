package com.example.recipefinder.recipes;

public class RecipeViewItem {

    // Save recipe name.
    private String recipeName;

    // Save recipe source.
    private String recipeSource;

    // Save image resource id.
    private int recipeImageId;

    private String recipeFilePath;

    private String[] recipeTags;

    public RecipeViewItem(String recipeName, String recipeSource, int recipeImageId, String[] recipeTag) {
        this.recipeName = recipeName;
        this.recipeSource = recipeSource;
        this.recipeImageId = recipeImageId;
        this.recipeTags = recipeTag;
    }

    public RecipeViewItem(String recipeName, String recipeSource, String recipeFilePath, String[] recipeTag) {
        this.recipeName = recipeName;
        this.recipeSource = recipeSource;
        this.recipeImageId = 0;
        this.recipeFilePath = recipeFilePath;
        this.recipeTags = recipeTag;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public int getRecipeImageId() {
        return recipeImageId;
    }

    public String getRecipeFilePath() {
        return recipeFilePath;
    }

    public String[] getRecipeTags() { return recipeTags;}
}
