package com.example.recipefinder.recipes;

import android.content.Context;
import com.example.recipefinder.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeList {

    private static String FILENAME = "recipefinder.json";

    private static List<RecipeViewItem> recipeList;

    public static List<RecipeViewItem> getRecipeList() {return recipeList;}

    public static void initialiseList(Context context) {

        File file = new File(context.getFilesDir(), FILENAME);
        if (file.exists()) {
            System.out.println("FILE EXISTS");
            // file exists so we should try and read from it
            Gson gson = new Gson();
            String json = null;
            try {
                json = readFromFile(context);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Type listType = new TypeToken<List<RecipeViewItem>>() {}.getType();
            recipeList = gson.fromJson(json, listType);

        } else {
            System.out.println("FILE DOES NOT EXIST");
            // file does not exist so we'll initialize the data and save the file
            if (recipeList == null) {
                recipeList = new ArrayList<>();

                String[] recipe1Tags = {"Brunch", "Eggs"};
                String[] recipe2Tags = {"Soup"};
                recipeList.add(new RecipeViewItem("Eggs Benedict", "Guardian Feast", R.drawable.brunch_recipe, recipe1Tags));
                recipeList.add(new RecipeViewItem("Tomato Soup", "Delia Cookbook", R.drawable.soup_recipe, recipe2Tags));
            }
            saveRecipeList(context);

        }
    }

    public static void clearList(Context context) {

        File file = new File(context.getFilesDir(), FILENAME);
        if(file.delete()){
            System.out.println(FILENAME + " deleted");
        }else System.out.println(FILENAME + " doesn't exist");

        recipeList = null;
    }

    private static void saveRecipe(Context context, RecipeViewItem photo){
        Gson gson = new Gson();
        String json;
        List<RecipeViewItem> currentList;

        // Read in existing file
        try {

            json = readFromFile(context);
            Type listType = new TypeToken<List<RecipeViewItem>>() {}.getType();
            currentList = gson.fromJson(json, listType);

            currentList.add(photo);

            String newJson = gson.toJson(currentList, listType);

            File file = new File(context.getFilesDir(), FILENAME);
            if(file.delete()){
                System.out.println(FILENAME + " deleted");
            }else System.out.println(FILENAME + " doesn't exist");

            FileOutputStream outputStream = context.openFileOutput(FILENAME, Context.MODE_APPEND);
            outputStream.write(newJson.getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String readFromFile(Context context) throws IOException {
        String jsonInput;
        FileInputStream fileInputStream = context.openFileInput(FILENAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        jsonInput = sb.toString();
        System.out.println("Reading from file: " + jsonInput);
        return jsonInput;
    }

    public static void addRecipe(Context context, RecipeViewItem photo) {
        recipeList.add(photo);
        saveRecipe(context, photo);
    }


    private static void saveRecipeList(Context context) {
        // Save initial file

        File file = new File(context.getFilesDir(), FILENAME);
        if (file.exists()) {
            return;
        }
        // file doesn't exist so we'll create it

        // Query free space?

        Type listType = new TypeToken<List<RecipeViewItem>>() {}.getType();

        Gson gson = new Gson();
        String json = gson.toJson(recipeList, listType);
        try {

            FileOutputStream outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();
            boolean fileExists = file.exists();
            System.out.println("fileExists: " + fileExists);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
