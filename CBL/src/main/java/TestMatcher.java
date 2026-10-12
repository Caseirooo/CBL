import java.util.ArrayList;
/**
 *
 * @author anast
 */
public class TestMatcher {
    public static void main (String[] args){
        
        RecipeReader recipeReader = new RecipeReader();
        
        ArrayList<Recipe> allRecipes = recipeReader.loadRecipes();
        ArrayList<Ingredient> pantry = new ArrayList<>();
        
        Ingredient eggs = new Ingredient("eggs", 5, "Dairy");
        Ingredient flour = new Ingredient("flour", 500, "Grains");
        Ingredient milk = new Ingredient("milk", 1000, "Dairy");
        Ingredient butter = new Ingredient("butter", 50, "Dairy");
        Ingredient sugar = new Ingredient("sugar", 20, "other");
        pantry.add(eggs);
        pantry.add(flour);
        pantry.add(milk);
        pantry.add(butter);
        pantry.add(sugar);
        
        
        RecipeMatcher recipeMatcher = new RecipeMatcher();
        int compatibility;
        compatibility = recipeMatcher.calculateCompatibility(pantry, allRecipes.get(0));
        System.out.println(compatibility + "%");
    }
}
