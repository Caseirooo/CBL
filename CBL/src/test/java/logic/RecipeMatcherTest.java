package logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import model.Ingredient;
import model.Recipe;

/**
 * This tests the percentage, missing ingredients, and sorting
 * @author anast
 */
public class RecipeMatcherTest {
    @Test
    public void pancakesCompatibilityWithTestPantryIs60Percent(){
        ArrayList<Ingredient> testPantry = new ArrayList<>();
        Ingredient pantryEggs = new Ingredient("eggs", 5, "Dairy");
        Ingredient pantryFlour = new Ingredient("flour", 500, "Grains");
        Ingredient pantryMilk = new Ingredient("milk", 1000, "Dairy");
        testPantry.add(pantryEggs);
        testPantry.add(pantryFlour);
        testPantry.add(pantryMilk);
        
        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        Ingredient recipeEggs = new Ingredient("eggs", 2, "Dairy");
        Ingredient recipeFlour = new Ingredient("flour", 200, "Grains");
        Ingredient recipeMilk = new Ingredient("milk", 300, "Dairy");
        Ingredient recipeButter = new Ingredient("butter", 20, "Dairy");
        Ingredient recipeSugar = new Ingredient("sugar", 20, "Dairy");
        recipeIngredients.add(recipeEggs);
        recipeIngredients.add(recipeFlour);
        recipeIngredients.add(recipeMilk);
        recipeIngredients.add(recipeButter);
        recipeIngredients.add(recipeSugar);
        
        ArrayList<String> instructions = new ArrayList<>();
        Recipe recipe = new Recipe("Pancakes", 20, 1, recipeIngredients,instructions);
        RecipeMatcher matcher = new RecipeMatcher();
        int compatibility = matcher.calculateCompatibility(testPantry, recipe);
        assertEquals(60, compatibility);
    }
}
