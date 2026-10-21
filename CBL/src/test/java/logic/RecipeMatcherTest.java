package logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import model.Ingredient;
import model.Recipe;

/**
 * This tests the percentage, missing ingredients, and sorting
 *
 * @author anast
 */
public class RecipeMatcherTest {

    //builds test pantry that is used in tests later 
    private ArrayList<Ingredient> makePantry() {
        ArrayList<Ingredient> testPantry = new ArrayList<>();
        testPantry.add(new Ingredient("eggs", 5, "Dairy"));
        testPantry.add(new Ingredient("flour", 500, "Grains"));
        testPantry.add(new Ingredient("milk", 1000, "Dairy"));
        return testPantry;
    }

    private Recipe makePancakeRecipe() {
        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(new Ingredient("eggs", 2, "Dairy"));
        recipeIngredients.add(new Ingredient("flour", 200, "Grains"));
        recipeIngredients.add(new Ingredient("milk", 300, "Dairy"));
        recipeIngredients.add(new Ingredient("butter", 20, "Dairy"));
        recipeIngredients.add(new Ingredient("sugar", 20, "Other"));
        ArrayList<String> instructions = new ArrayList<>();
        Recipe recipe = new Recipe("Pancakes", 20, 1, recipeIngredients, instructions);
        return recipe;
    }

    @Test
    public void pancakesCompatibilityWithTestPantryIs60Percent() {
        ArrayList<Ingredient> testPantry = makePantry();
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(testPantry, recipe);
        assertEquals(60, compatibility);
    }

    @Test
    public void emptyPantryIs0Percent() {
        ArrayList<Ingredient> emptyPantry = new ArrayList<>();
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(emptyPantry, recipe);
        assertEquals(0, compatibility);
    }

    @Test
    public void fullPantryIs100Percent() {
        ArrayList<Ingredient> fullPantry = makePantry();
        fullPantry.add(new Ingredient("butter", 20, "Dairy"));
        fullPantry.add(new Ingredient("sugar", 20, "Other"));
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(fullPantry, recipe);
        assertEquals(100, compatibility);
    }
}
