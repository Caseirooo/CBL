import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will read the recipe file
 *
 * @author anast
 */
public class RecipeReader {
   
     public ArrayList<Recipe> loadRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            File file = new File("recipes.csv");
            Scanner reader = new Scanner(file);

            reader.nextLine(); //skips  the first line as it contains only the headers of each column in the file   

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find recipe.csv");
        }
        return recipes;
    }
}
