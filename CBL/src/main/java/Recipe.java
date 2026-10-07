/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anast
 */
//this class contains information about the name of the recipe, list of Ingredients needed, instructions, prep time, and difficulty
import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients; //ArrayList allows to adapt to every recipe - each recipe has dif number of ingredients 
    private ArrayList<String> instructions; //and a dif set of instructions^^
    private int prepTime;
    private int difficulty;

    public Recipe(String name, ArrayList<Ingredient> ingredients,
            ArrayList<String> instructions, int prepTime, int difficulty) {
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
}
