import files.RecipeReader;
import model.Recipe;
    import java.util.ArrayList;
    /**
     *
     * @author anast
     */
    public class TestReader {
        public static void main (String[] args){
            RecipeReader recipeReader = new RecipeReader();
            ArrayList<Recipe> allRecipes = recipeReader.loadRecipes();
             //check all recipes are loaded - should print 30 if all recipes are loaded 
            System.out.println(allRecipes.size());
        }
    }
