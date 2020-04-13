package com.example.recipefinder.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.recipefinder.R;
import com.example.recipefinder.recipes.RecipeList;
import com.example.recipefinder.recipes.RecipeViewItem;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class SelectPhotoActivity extends AppCompatActivity {

    public final static int PICK_PHOTO_CODE = 1046;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            photoUri = data.getData();
            // Do something with the photo based on Uri
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Load the selected image into a preview
            ImageView ivPreview = findViewById(R.id.ivPreview);
            ivPreview.setImageBitmap(selectedImage);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String get_image_name(Uri image_uri) {
        String fileName = null;
        String scheme = image_uri.getScheme();

        if (scheme == null){
            return "";
        } else if (scheme.equals("file")) {
            return image_uri.getLastPathSegment();
        }
        else if (scheme.equals("content")) {
            String[] proj = { MediaStore.Images.Media.TITLE };
            Cursor cursor = this.getContentResolver().query(image_uri, proj, null, null, null);
            if (cursor != null && cursor.getCount() != 0) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
                cursor.moveToFirst();
                fileName = cursor.getString(columnIndex);
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return fileName;
    }

    public String get_file_path( Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = this.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    public void done_button(View view) {

        if (photoUri != null) {
            // get the metadata here
            TextInputLayout recipeNameInput = findViewById(R.id.recipe_title_input);
            EditText recipeNameText = recipeNameInput.getEditText();
            String recipeName = "";
            if (recipeNameText != null) {
                recipeName = recipeNameText.getText().toString();
            }

            TextInputLayout recipeSourceInput = findViewById(R.id.recipe_source_input);
            EditText recipeSourceText = recipeSourceInput.getEditText();
            String recipeSource= "";
            if (recipeSourceText != null) {
                recipeSource = recipeSourceText.getText().toString();
            }

            TextInputLayout recipeTagsInput = findViewById(R.id.recipe_tags_input);
            EditText recipeTagsText = recipeTagsInput.getEditText();
            String[] recipeTags = {};
            if (recipeTagsText != null) {
                recipeTags = recipeTagsText.getText().toString().split(",");

            }

            String filePath = get_file_path(photoUri);
            RecipeList.addRecipe(this.getApplicationContext(), new RecipeViewItem(recipeName, recipeSource, filePath, recipeTags));
        }


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragmentToLoad", R.id.action_add);
        startActivity(intent);
    }

}
