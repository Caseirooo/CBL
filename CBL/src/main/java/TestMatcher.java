
import java.util.ArrayList;

/**
 *
 * @author anast
 */
public class TestMatcher {

    public static void main(String[] args) {

        RecipeReader recipeReader = new RecipeReader();

        ArrayList<Recipe> allRecipes = recipeReader.loadRecipes();
        ArrayList<Ingredient> pantry = new ArrayList<>();

        Ingredient eggs = new Ingredient("eggs", 5, "Dairy");
        Ingredient flour = new Ingredient("flour", 500, "Grains");
        Ingredient milk = new Ingredient("milk", 1000, "Dairy");
        pantry.add(eggs);
        pantry.add(flour);
        pantry.add(milk);

        RecipeMatcher recipeMatcher = new RecipeMatcher();
        int compatibility;
        compatibility = recipeMatcher.calculateCompatibility(pantry, allRecipes.get(0));
        System.out.println(compatibility + "%");

        ArrayList<Ingredient> missingIngredients = recipeMatcher.findMissingIngredients(pantry, allRecipes.get(0));
        for (Ingredient missingIngredient : missingIngredients) {
            String missingName = missingIngredient.getName();
            System.out.println(missingName);
        }
    }
}
