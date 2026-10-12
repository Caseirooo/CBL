
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
        int compatibility;

        for (Ingredient needed : recipe.getIngredients()) {
            for (Ingredient userHas : pantry) {
                if (userHas.getName().equals(needed.getName())
                        && userHas.getQuantity() >= needed.getQuantity()) {
                    matchedIngredients = matchedIngredients + 1;
                }
            }
        }
        compatibility = matchedIngredients * 100 / recipe.getIngredients().size();
        return compatibility;
    }

}
