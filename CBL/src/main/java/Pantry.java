/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
import java.util.ArrayList;

public class Pantry {

    private Catalog catalog;
    private ArrayList<Ingredient> ingredients;

    public Pantry(Catalog catalog) {
        this.catalog = catalog;
        this.ingredients = new ArrayList<>();
    }

    public boolean addIngredient(String name, int amount) {
        if (amount <= 0) {
            return false;
        }

        Ingredient catalogIngredient = catalog.findIngredient(name);

        if (catalogIngredient == null) {
            return false;
        }

        Ingredient pantryIngredient = findIngredient(name);

        if (pantryIngredient != null) {
            pantryIngredient.changeQuantity(amount);
        } else {
            Ingredient newIngredient = new Ingredient(
                    catalogIngredient.getName(),
                    amount,
                    catalogIngredient.getCategory()
            );

            ingredients.add(newIngredient);
        }

        return true;
    }

    public boolean removeIngredient(String name, int amount) {
        if (amount <= 0) {
            return false;
        }

        Ingredient ingredient = findIngredient(name);

        if (ingredient == null || ingredient.getQuantity() < amount) {
            return false;
        }

        ingredient.changeQuantity(-amount);

        if (ingredient.getQuantity() == 0) {
            ingredients.remove(ingredient);
        }

        return true;
    }

    public Ingredient findIngredient(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }

        return null;
    }

    public int getQuantity(String name) {
        Ingredient ingredient = findIngredient(name);

        if (ingredient == null) {
            return 0;
        }

        return ingredient.getQuantity();
    }

    public ArrayList<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public Catalog getCatalog() {
        return catalog;
    }
}
