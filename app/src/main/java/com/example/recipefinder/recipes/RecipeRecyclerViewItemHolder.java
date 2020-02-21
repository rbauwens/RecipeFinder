package com.example.recipefinder.recipes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinder.R;

public class RecipeRecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private ImageView recipeImageView;
    private TextView recipeTitleView;
    private TextView recipeSourceView;
    private TextView recipeTagsView;

    RecipeRecyclerViewItemHolder(View itemView) {
        super(itemView);

        recipeImageView = itemView.findViewById(R.id.card_view_image);
        recipeTitleView = itemView.findViewById(R.id.recipe_title);
        recipeSourceView = itemView.findViewById(R.id.source_view);
        recipeTagsView = itemView.findViewById(R.id.tags_view);
    }

    ImageView getPhotoImageView() {
        return recipeImageView;
    }

    TextView getRecipeTitleView() {
        return recipeTitleView;
    }

    TextView getRecipeSourceView() {
        return recipeSourceView;
    }
    TextView  getRecipeTagsView() {
       return recipeTagsView;
    }
}
