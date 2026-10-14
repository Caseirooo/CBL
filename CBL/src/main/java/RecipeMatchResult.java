
import java.util.ArrayList;

/**
 * For each recipe this class remembers information about how well it matches
 * the ingredients in the pantry
 *
 * @author anast
 */
public class RecipeMatchResult {

    private final Recipe recipe; //fields are final because they refer to the current status of the current recipe whcih does not change untill the ingredients in the pantry are changed
    private final int percentage;
    private final ArrayList<Ingredient> missingIngredients;

    public RecipeMatchResult(Recipe recipe, int percentage, ArrayList<Ingredient> missingIngredients) {
        this.recipe = recipe;
        this.percentage = percentage;
        this.missingIngredients = missingIngredients;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getPercentage() {
        return percentage;
    }

    public ArrayList<Ingredient> getMissingIngredients() {
        return missingIngredients;
    }

}
