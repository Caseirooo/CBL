package model;

/**
 * Holds the ingredients the user currently has. Adds and removes quantities,
 * looks up ingredients by name, and uses up a recipe's ingredients and saves
 * the result.
 *
 * @author franc
 */
import files.PantryStorage;
import java.util.ArrayList;

public class Pantry {

    private PantryStorage pantryS;
    private Catalog catalog;
    private ArrayList<Ingredient> ingredients;

    public void setStorage(PantryStorage pantryS) {
        this.pantryS = pantryS;
    }

    public Pantry(Catalog catalog) {
        this.catalog = catalog;
        this.ingredients = new ArrayList<>();
    }

    public boolean addIngredient(String name, int amount) {
        if (amount <= 0) {
            return false;
        }

        Ingredient catalogIngredient = catalog.findIngredientCatalog(name);

        if (catalogIngredient == null) {
            return false;
        }

        Ingredient pantryIngredient = findIngredientPantry(name);

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

        Ingredient ingredient = findIngredientPantry(name);

        if (ingredient == null || ingredient.getQuantity() < amount) {
            return false;
        }

        ingredient.changeQuantity(-amount);

        if (ingredient.getQuantity() == 0) {
            ingredients.remove(ingredient);
        }

        return true;
    }

    public Ingredient findIngredientPantry(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }

        return null;
    }

    public int getQuantity(String name) {
        Ingredient ingredient = findIngredientPantry(name);

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

    public boolean useUp(java.util.List<Ingredient> needed) {
        for (Ingredient item : needed) {
            if (getQuantity(item.getName()) < item.getQuantity()) {
                return false;
            }
        }
        for (Ingredient item : needed) {
            removeIngredient(item.getName(), item.getQuantity());
        }
        pantryS.save();
        return true;
    }
}
