import java.util.ArrayList;
/**
 *
 * @author anast
 */
public class RecipeMatch {
    private Recipe recipe;
    private int percentage;
    private ArrayList<Ingredient> missingIngredients;
    
    public RecipeMatch (Recipe recipe, int percentage, ArrayList<Ingredient> missingIngredients){
        this.recipe = recipe;
        this.percentage = percentage;
        this.missingIngredients = missingIngredients;
    }
    
    public Recipe getRecipe(){
        return recipe;
    }
    
    public int getPercentage() { 
        return percentage;
    }
    
    public ArrayList<Ingredient> getMissingIngredients(){
        return missingIngredients;
    }
    

}
