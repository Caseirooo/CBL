package logic;

import java.util.ArrayList;
import model.Ingredient;
import model.Recipe;
import model.RecipeMatchResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The percentage, the missing ingredients and the sorting function of
 * RecipeMatcher are tested. Within the tests small pantries and recipes are set
 * up. This allows each test to only depend on the matcher and not on the CSV
 * file.
 *
 * @author anast
 */
public class RecipeMatcherTest {

    /**
     * Builds test pantry that is used in most tests later. The pantry does not
     * contain all of the ingredients for the pancake recipe (is missing butter
     * and sugar).
     */
    private ArrayList<Ingredient> makePantry() {
        ArrayList<Ingredient> testPantry = new ArrayList<>();
        testPantry.add(new Ingredient("eggs", 5, "Dairy"));
        testPantry.add(new Ingredient("flour", 500, "Grains"));
        testPantry.add(new Ingredient("milk", 1000, "Dairy"));
        return testPantry;
    }

    /**
     * Builds the pancake recipe which is used in most of the tests later.
     */
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

    /**
     * Tests cases where pantry does not have every ingredient required for the
     * recipe. The pantry has 3 of the 5 ingredients in large enough amounts, so
     * the compatibility is 60%.
     */
    @Test
    public void pancakesCompatibilityWithTestPantryIs60Percent() {
        ArrayList<Ingredient> testPantry = makePantry();
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(testPantry, recipe);
        assertEquals(60, compatibility);
    }

    /**
     * Tests the edge case where the user has not added anything, so the app
     * must give a 0% compatibility and not crash.
     */
    @Test
    public void emptyPantryIs0Percent() {
        ArrayList<Ingredient> emptyPantry = new ArrayList<>();
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(emptyPantry, recipe);
        assertEquals(0, compatibility);
    }

    /**
     * Checks that if the pantry has all of the ingredients, then the
     * compatibility percentage will be 100%. And it checks that having the
     * exact amounts of butter and sugar counts as enough.
     */
    @Test
    public void fullPantryIs100Percent() {
        ArrayList<Ingredient> fullPantry = makePantry();
        fullPantry.add(new Ingredient("butter", 20, "Dairy")); //the recipe requires exactly 20
        fullPantry.add(new Ingredient("sugar", 20, "Other")); //the recipe requires exactly 20
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(fullPantry, recipe);
        assertEquals(100, compatibility);
    }

    /**
     * Checks not just the name but also the quantity of the ingredients in the
     * pantry. Makes sure that even if the ingredient is there, if the quantity
     * is less than the required one it does not count.
     */
    @Test
    public void notEnoughIngredientsDoesNotCount() {
        ArrayList<Ingredient> lessIngredientsPantry = new ArrayList<>();
        lessIngredientsPantry.add(new Ingredient("eggs", 1, "Dairy"));
        lessIngredientsPantry.add(new Ingredient("flour", 500, "Grains"));
        lessIngredientsPantry.add(new Ingredient("milk", 1000, "Dairy"));
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(lessIngredientsPantry, recipe);
        assertEquals(40, compatibility);
    }

    /**
     * This test is needed because the catalog of ingredients uses capital
     * letters, while the recipes use lower case letters. It checks that "Eggs"
     * matches "eggs".
     */
    @Test
    public void capitalsDoNotMatter() {
        ArrayList<Ingredient> capitalLetterPantry = new ArrayList<>();
        capitalLetterPantry.add(new Ingredient("Eggs", 5, "Dairy"));
        capitalLetterPantry.add(new Ingredient("Flour", 500, "Grains"));
        capitalLetterPantry.add(new Ingredient("Milk", 1000, "Dairy"));
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(capitalLetterPantry, recipe);
        assertEquals(60, compatibility);
    }

    /**
     * findMissingIngredients returns the ingredients that are not in the pantry
     * in a large enough amount, but are in the recipe. We check the size of the
     * list to make sure that there is nothing extra.
     */
    @Test
    public void missingIngredientsAreButterAndSugar() {
        ArrayList<Ingredient> pantry = makePantry();
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        ArrayList<Ingredient> missingIngredients = matcher.findMissingIngredients(pantry, recipe);
        assertEquals(2, missingIngredients.size());
        assertEquals("butter", missingIngredients.get(0).getName());
        assertEquals("sugar", missingIngredients.get(1).getName());
    }

    /**
     * The missing amount refers to the amount of each ingredient needed for the
     * recipe minus the amount of each ingredient that are in the pantry.
     */
    @Test
    public void ifOneEggLessThanNeededThenOneEggIsMissing() {
        ArrayList<Ingredient> pantry = new ArrayList<>();
        pantry.add(new Ingredient("eggs", 1, "Dairy"));
        pantry.add(new Ingredient("flour", 500, "Grains"));
        pantry.add(new Ingredient("milk", 1000, "Dairy"));
        Recipe recipe = makePancakeRecipe();
        RecipeMatcher matcher = new RecipeMatcher();
        ArrayList<Ingredient> missingIngredients = matcher.findMissingIngredients(pantry, recipe);
        assertEquals(3, missingIngredients.size());
        assertEquals("eggs", missingIngredients.get(0).getName());
        assertEquals(1, missingIngredients.get(0).getQuantity()); //checks if the ingredients are partially there 
        assertEquals(20, missingIngredients.get(1).getQuantity()); //checks if the ingredients are not there at all 
        assertEquals(20, missingIngredients.get(2).getQuantity());
    }

    /**
     * The recipes are given to the matcher in the wrong order to see if the
     * highest compatible recipe still comes out first. This proves that the
     * sorting actually happens.
     */
    @Test
    public void highestPercentageComesFirst() {
        ArrayList<Ingredient> testPantry = makePantry();
        Recipe pancakes = makePancakeRecipe();
        ArrayList<Ingredient> omeletteIngredients = new ArrayList<>();
        omeletteIngredients.add(new Ingredient("eggs", 2, "Dairy"));
        omeletteIngredients.add(new Ingredient("milk", 50, "Dairy"));
        ArrayList<String> instructions = new ArrayList<>();
        Recipe omelette = new Recipe("Omelette", 10, 1, omeletteIngredients, instructions);
        ArrayList<Recipe> allRecipes = new ArrayList<>();
        allRecipes.add(pancakes); //wrong order on purpose 
        allRecipes.add(omelette); //recipe with higher compatibility percentage is second
        RecipeMatcher matcher = new RecipeMatcher();
        ArrayList<RecipeMatchResult> results = matcher.matchAllRecipes(testPantry, allRecipes);
        assertEquals("Omelette", results.get(0).getRecipe().getName());
        assertEquals("Pancakes", results.get(1).getRecipe().getName());
        assertEquals(100, results.get(0).getPercentage());
    }

}
