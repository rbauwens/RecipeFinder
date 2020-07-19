package com.example.recipefinder.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.recipefinder.R;
import com.example.recipefinder.activity.SelectPhotoActivity;
import com.example.recipefinder.activity.TakePhotoActivity;

public class AddRecipeFragment extends Fragment {

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        final View inputView =  inflater.inflate(R.layout.fragment_add_recipe, parent, false);

        Button selectPhotoButton = inputView.findViewById(R.id.add_photos_button);
        selectPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inputView.getContext(), SelectPhotoActivity.class);
                startActivity(intent);
            }
        });

        Button takePhotoButton = inputView.findViewById(R.id.take_photos_button);

        Context context = getContext();
        if (context != null) {
            PackageManager pm = getContext().getPackageManager();
            if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                takePhotoButton.setVisibility(View.INVISIBLE);
            }
        }

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inputView.getContext(), TakePhotoActivity.class);
                startActivity(intent);
            }
        });

        return inputView;
    }

}
