package com.example.recipefinder.fragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.os.Bundle;

import com.example.recipefinder.R;


public class BrunchFragment extends Fragment {

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt("currentPhoto", photoIndex);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brunch, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}