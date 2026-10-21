package model;

import java.util.ArrayList;

/**
 * For each recipe this class remembers information about how well it matches
 * the ingredients in the pantry
 *
 * @author anast
 */
public class RecipeMatchResult implements Comparable<RecipeMatchResult> {

    /*fields are final because they refer to the current status of the current 
    recipe which does not change until the ingredients in the pantry are changed*/
    private final Recipe recipe;
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

    /*sorting order: compares percentage (highest goes first), if it is a tie 
      then compares the difficulty (lowest goes first), if it is a tie then 
      compares preparation time (lowest goes first).*/
    @Override
    public int compareTo(RecipeMatchResult other) {
        if (this.percentage != other.percentage) {
            return other.percentage - this.percentage;
        }
        if (this.recipe.getDifficulty() != other.recipe.getDifficulty()) {
            return this.recipe.getDifficulty() - other.recipe.getDifficulty();
        }
        return this.recipe.getPrepTime() - other.recipe.getPrepTime();
    }

}
