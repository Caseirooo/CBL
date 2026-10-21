package logic;


import model.RecipeMatchResult;
import model.Recipe;
import model.Ingredient;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class calculates the compatibility percentage of the recipe ingredients
 * with the ingredients in the user's pantry
 *
 * @author anast
 */
public class RecipeMatcher {

    public int calculateCompatibility(ArrayList<Ingredient> pantry, Recipe recipe) {
        int matchedIngredients = 0;

        /*this loop works like this: for each ingredient that the recipe needs, 
        it searches the pantry for this ingredient in an amount that is large enough.*/
        for (Ingredient needed : recipe.getIngredients()) {
            for (Ingredient userHas : pantry) {
                if (userHas.getName().equalsIgnoreCase(needed.getName())
                        && userHas.getQuantity() >= needed.getQuantity()) {
                    matchedIngredients = matchedIngredients + 1;
                }
            }

        }
        int compatibility;
        compatibility = matchedIngredients * 100 / recipe.getIngredients().size();
        return compatibility;
    }

    //finds which ingredients are missing and how much of each is still needed
    public ArrayList<Ingredient> findMissingIngredients(ArrayList<Ingredient> pantry, Recipe recipe) {
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();

        for (Ingredient needed : recipe.getIngredients()) {
            int quantityInPantry = 0;
            for (Ingredient userHas : pantry) {
                if (userHas.getName().equalsIgnoreCase(needed.getName())) {
                    quantityInPantry = userHas.getQuantity();
                }
            }
            int quantityStillNeeded = needed.getQuantity() - quantityInPantry;
            if (quantityStillNeeded > 0) {
                missingIngredients.add(new Ingredient(needed.getName(), quantityStillNeeded, needed.getCategory()));
            }
        }
        return missingIngredients;
    }

    //runs through all recipes and finds compatability and missing ingredients 
    public ArrayList<RecipeMatchResult> matchAllRecipes(ArrayList<Ingredient> pantry, ArrayList<Recipe> recipes) {

        //where the result is collected
        ArrayList<RecipeMatchResult> matchedRecipes = new ArrayList<>();

        //goes through each recipe and calculates compatability and finds missing ingredients 
        for (Recipe recipe : recipes) {
            int percentage = calculateCompatibility(pantry, recipe);
            ArrayList<Ingredient> missingIngredients = findMissingIngredients(pantry, recipe);
            RecipeMatchResult matchedRecipesResult = new RecipeMatchResult(recipe, percentage, missingIngredients);

            matchedRecipes.add(matchedRecipesResult);
        }

        //sorts the lost of recipes in the order that is specified in the class RecipeMatchResult
        Collections.sort(matchedRecipes);

        return matchedRecipes;
    }

}
