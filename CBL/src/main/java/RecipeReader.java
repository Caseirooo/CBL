
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
            File file = new File("recipes.csv"); //tells which file to read 
            Scanner reader = new Scanner(file); //opens the file

            reader.nextLine(); //skips  the first line as it contains only the headers of each column in the file   

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                //splits each recipe into: name, time, difficulty, ingredients, and steps 
                String[] parts = line.split(",");
                String name = parts[0];
                int time = Integer.parseInt(parts[1]);
                int difficulty = Integer.parseInt(parts[2]);
                String ingredients = parts[3];
                String steps = parts[4];

                //splits ingredients into actual separate ingredients, quantities, and categories
                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                String[] ingredientPieces = ingredients.split(";");

                //for each loop avoids mistakes with counts of i (like in a for loop) and ensures all ingredients are presented to user
                for (String piece : ingredientPieces) {
                    String[] ingredientParts = piece.split(":");
                    String ingredientName = ingredientParts[0];
                    int ingredientQuantity = Integer.parseInt(ingredientParts[1]);
                    String ingredientCategory = ingredientParts[2];

                    //we create an object and pass on the ingredient information. Then we add this object to the list of teh ingredients needed for the recipe.
                    Ingredient ingredient = new Ingredient(ingredientName,
                            ingredientQuantity, ingredientCategory);
                    ingredientList.add(ingredient);
                }
                
                
                System.out.println(name);
                System.out.println("Time to cook this recipe: " + time);
                System.out.println("Difficulty: " + difficulty);
                System.out.println("Ingredients required: ");
                //prints each ingredient name and quantity using the getters from class Ingredient
                for (Ingredient ingredient : ingredientList) {
                    System.out.println(ingredient.getName() + " "
                            + ingredient.getQuantity());
                }
                
                System.out.println("Instructions: ");
                ArrayList<String> instructionList = new ArrayList<>();
                String[] instructionSteps = steps.split(";");
                
                for (String step : instructionSteps){
                    System.out.println(step);
                    instructionList.add(step);
                }
            }

            reader.close(); //closes file 
        } catch (FileNotFoundException e) {
            System.out.println("Could not find recipe.csv");
        }
        return recipes;
    }
}
