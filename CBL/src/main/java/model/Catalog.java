package model;

/**
 * Holds the list of all ingredients that exist in the app, along with their
 * categories, and lets you add, search by name, and filter ingredients by
 * category.
 *
 * @author franc
 */
import java.util.ArrayList;

public class Catalog {

    private String[] categories;
    private ArrayList<Ingredient> ingredients;

    public Catalog(String[] categories) {
        this.categories = categories;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public String[] getCategories() {
        return categories;
    }

    public ArrayList<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public Ingredient findIngredient(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public ArrayList<Ingredient> getIngredientsByCategory(String category) {
        ArrayList<Ingredient> result = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getCategory().equalsIgnoreCase(category)) {
                result.add(ingredient);
            }
        }
        return result;
    }
}
