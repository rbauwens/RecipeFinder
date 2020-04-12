package com.example.recipefinder;

import com.example.recipefinder.fragment.Common;
import com.example.recipefinder.recipes.RecipeViewItem;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CommonFragmentTests {

    private static List<RecipeViewItem> testRecipeList;
    private static RecipeViewItem soupItem;
    private static RecipeViewItem brunchItem;

    @BeforeClass
    public static void setupRecipeList() {
        testRecipeList = new ArrayList<>();

        soupItem = new RecipeViewItem("Tomato Soup", "Delia Cookbook", R.drawable.soup_recipe, new String[]{"Soup"});
        brunchItem = new RecipeViewItem("Eggs Benedict", "Guardian Feast", R.drawable.brunch_recipe, new String[]{"Brunch", "Eggs"});

        testRecipeList.add(soupItem);
        testRecipeList.add(brunchItem);

    }

    @Test
    public void testFindRecipeByTag() {

        List<RecipeViewItem> foundRecipeList = Common.getRecipesByTag(testRecipeList, "Soup");
        assertEquals(foundRecipeList.size(), 1);
        assertTrue(foundRecipeList.contains(soupItem));

        foundRecipeList = Common.getRecipesByTag(testRecipeList, "Brunch");
        assertEquals(foundRecipeList.size(), 1);
        assertTrue(foundRecipeList.contains(brunchItem));

    }

    @Test
    public void testFindRecipeByTagMissingTag() {

        RecipeViewItem missingTagItem;
        missingTagItem = new RecipeViewItem("Mock Turtle Soup", "The internet", R.drawable.soup_recipe, null);
        List<RecipeViewItem> foundRecipeList = Common.getRecipesByTag(testRecipeList, "Soup");

        assertEquals(foundRecipeList.size(), 1);
        assertFalse(foundRecipeList.contains(missingTagItem));
        assertTrue(foundRecipeList.contains(soupItem));

    }

}
