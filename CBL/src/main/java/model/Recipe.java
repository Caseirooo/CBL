package model;

/**
 * This class contains information about the name of the recipe, list of
 * ingredients needed, instructions, prep time, and difficulty
 *
 * @author anast
 */
import java.util.ArrayList;

public class Recipe {

    private final String name;
    private final ArrayList<Ingredient> ingredients; //ArrayList allows to adapt to every recipe - each recipe has different number of ingredients 
    private final ArrayList<String> instructions; // allows to adapt to every recipe - each recipe has a different set of instructions
    private final int prepTime; //prep time is in minutes
    private final int difficulty; //difficulty is 1=easy, 2=medium, 3=hard

    public Recipe(String name, int prepTime, int difficulty, ArrayList<Ingredient> ingredients,
            ArrayList<String> instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
    }

    //allows other classes to read private fields 
    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getDifficulty() {
        return difficulty;
    }

    //turns difficulty number into text (Easy, Medium, Hard)
    public String difficultyToWord() {
        if (difficulty == 1) {
            return "Easy";
        } else if (difficulty == 2) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

}
