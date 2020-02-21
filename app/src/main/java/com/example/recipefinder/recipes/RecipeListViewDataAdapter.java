package com.example.recipefinder.recipes;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefinder.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.List;

public class RecipeListViewDataAdapter extends RecyclerView.Adapter<RecipeRecyclerViewItemHolder>{

    private List<RecipeViewItem> recipeList;

    public RecipeListViewDataAdapter(List<RecipeViewItem> recipeListItem) {
        this.recipeList = recipeListItem;
    }

    @Override
    public RecipeRecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View photoItemView = layoutInflater.inflate(R.layout.recipe_card_view_item, parent, false);

        final ImageView photoImageView = photoItemView.findViewById(R.id.card_view_image);

        // When click the image.
        photoItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String photoTitle = photoImageView.getTag().toString();
                // Create a snackbar and show it.
                Snackbar snackbar = Snackbar.make(photoImageView, "You clicked " + photoTitle +" image", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        // Create and return our custom Car Recycler View Item Holder object.
        return new RecipeRecyclerViewItemHolder(photoItemView);
    }

    /**
     * Display each photo
     */
    @Override
    public void onBindViewHolder(RecipeRecyclerViewItemHolder holder, int position) {
        if(recipeList!=null) {
            // Get item in list.
            RecipeViewItem recipeItem = recipeList.get(position);

            if(recipeItem != null) {
                // Set item title as a tag of the imageView so we can get it later
                holder.getPhotoImageView().setTag(recipeItem.getRecipeName());

                // Set image resource id or file path if in local storage.
                if (recipeItem.getRecipeImageId() != 0) {
                    holder.getPhotoImageView().setImageResource(recipeItem.getRecipeImageId());
                } else if (recipeItem.getRecipeFilePath() != null) {
                    File imageFile = new File(recipeItem.getRecipeFilePath());
                    if (imageFile.exists()) {
                        holder.getPhotoImageView().setImageBitmap(BitmapFactory.decodeFile(recipeItem.getRecipeFilePath()));
                    }
                }

                // Set title
                if (recipeItem.getRecipeName() != null) {
                    holder.getRecipeTitleView().setText(recipeItem.getRecipeName());
                }

                // Set source
                if (recipeItem.getRecipeSource() != null) {
                    holder.getRecipeSourceView().setText(recipeItem.getRecipeSource());
                }

                // Set tags
                String[] recipeTags = recipeItem.getRecipeTags();
                if (recipeTags.length != 0) {
                    String tagString = "";
                    for (int i = 0; i < recipeTags.length; i++) {
                        if (i != 0){
                            tagString = tagString.concat(", ");
                        }
                        tagString = tagString.concat(recipeTags[i]);
                    }

                    holder.getRecipeTagsView().setText(tagString);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(recipeList!=null)
        {
            ret = recipeList.size();
        }
        return ret;
    }
}
