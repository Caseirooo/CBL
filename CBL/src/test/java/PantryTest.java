/**
 * This tests whether removing and adding works
 * @author franc
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Catalog;
import model.Pantry;
import model.Ingredient;

class PantryTest {

    private Catalog catalog;
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        catalog = new Catalog(new String[]{"Dairy", "Meat"});

        catalog.addIngredient(
            new Ingredient("Eggs", 0, "Dairy")
        );
        catalog.addIngredient(
            new Ingredient("Chicken", 0, "Meat")
        );

        pantry = new Pantry(catalog);
    }

    @Test
    void addIngredientIncreasesQuantity() {
        assertTrue(pantry.addIngredient("Eggs", 5));
        assertEquals(5, pantry.getQuantity("Eggs"));
    }

    @Test
    void addingSameIngredientCombinesQuantities() {
        pantry.addIngredient("Eggs", 5);
        pantry.addIngredient("Eggs", 3);

        assertEquals(8, pantry.getQuantity("Eggs"));
    }

    @Test
    void cannotAddNonexistentIngredient() {
        assertFalse(pantry.addIngredient("Chocolate", 2));
        assertEquals(0, pantry.getQuantity("Chocolate"));
    }

    @Test
    void cannotAddZeroOrNegativeAmount() {
        assertFalse(pantry.addIngredient("Eggs", 0));
        assertFalse(pantry.addIngredient("Eggs", -2));

        assertEquals(0, pantry.getQuantity("Eggs"));
    }

    @Test
    void removeIngredientReducesQuantity() {
        pantry.addIngredient("Eggs", 5);

        assertTrue(pantry.removeIngredient("Eggs", 2));
        assertEquals(3, pantry.getQuantity("Eggs"));
    }

    @Test
    void cannotRemoveMoreThanAvailable() {
        pantry.addIngredient("Eggs", 3);

        assertFalse(pantry.removeIngredient("Eggs", 4));
        assertEquals(3, pantry.getQuantity("Eggs"));
    }

    @Test
    void removingAllIngredientsRemovesPantryEntry() {
        pantry.addIngredient("Eggs", 2);

        assertTrue(pantry.removeIngredient("Eggs", 2));

        assertEquals(0, pantry.getQuantity("Eggs"));
        assertNull(pantry.findIngredient("Eggs"));
    }

    @Test
    void ingredientSearchIsCaseInsensitive() {
        pantry.addIngredient("Eggs", 4);

        assertEquals(4, pantry.getQuantity("eggs"));
    }
}