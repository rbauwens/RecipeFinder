package com.example.recipefinder.recipes;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinder.R;

public class RecipeRecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private ImageView recipeImageView;

    RecipeRecyclerViewItemHolder(View itemView) {
        super(itemView);
        recipeImageView = itemView.findViewById(R.id.card_view_image);
    }

    ImageView getPhotoImageView() {
        return recipeImageView;
    }
}
