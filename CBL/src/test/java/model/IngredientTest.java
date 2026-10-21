package model;

/**
 * This tests whether the ingredients get assigned the correct characteristics
 * @author franc
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Ingredient;

class IngredientTest {

    @Test
    void ingredientStoresItsName() {
        Ingredient ingredient
                = new Ingredient("Eggs", 6, "Dairy");

        assertEquals("Eggs", ingredient.getName());
    }

    @Test
    void ingredientStoresItsQuantity() {
        Ingredient ingredient = new Ingredient("Eggs", 6, "Dairy");

        assertEquals(6, ingredient.getQuantity());
    }

    @Test
    void ingredientStoresItsCategory() {
        Ingredient ingredient = new Ingredient("Eggs", 6, "Dairy");

        assertEquals("Dairy", ingredient.getCategory());
    }
}
