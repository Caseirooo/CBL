
import java.util.ArrayList;

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

    public  ArrayList<Ingredient> findMissingIngredients(ArrayList<Ingredient> pantry, Recipe recipe) {
        ArrayList<Ingredient> missingIngredients = new ArrayList<>();

        for (Ingredient needed : recipe.getIngredients()) {
            boolean found = false;
            for (Ingredient userHas : pantry) {
                if (userHas.getName().equalsIgnoreCase(needed.getName())
                        && userHas.getQuantity() >= needed.getQuantity()) {
                    found = true;
                }
            }
            if (!found) {
                missingIngredients.add(needed);
            }
        }
        return missingIngredients;
    }

}
