package model;

/**
 * Tests for finding ingredients and introducing new ones
 * @author franc
 */

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    @Test
    void addIngredientStoresIngredient() {
        Catalog catalog = new Catalog(
            new String[]{"Dairy", "Meat"}
        );
        Ingredient eggs = new Ingredient("Eggs", 0, "Dairy");

        catalog.addIngredient(eggs);

        assertTrue(catalog.getIngredients().contains(eggs));
    }

    @Test
    void findIngredientReturnsCorrectIngredient() {
        Catalog catalog = new Catalog(
            new String[]{"Dairy", "Meat"}
        );
        Ingredient eggs = new Ingredient("Eggs", 0, "Dairy");

        catalog.addIngredient(eggs);

        assertEquals(eggs, catalog.findIngredientCatalog("Eggs"));
    }

    @Test
    void findIngredientIsCaseInsensitive() {
        Catalog catalog = new Catalog(
            new String[]{"Dairy", "Meat"}
        );
        Ingredient eggs = new Ingredient("Eggs", 0, "Dairy");

        catalog.addIngredient(eggs);

        assertEquals(eggs, catalog.findIngredientCatalog("eggs"));
    }

    @Test
    void findIngredientReturnsNullWhenNotFound() {
        Catalog catalog = new Catalog(
            new String[]{"Dairy", "Meat"}
        );

        assertNull(catalog.findIngredientCatalog("Eggs"));
    }

    @Test
    void getIngredientsByCategoryReturnsCorrectIngredients() {
        Catalog catalog = new Catalog(
            new String[]{"Dairy", "Meat"}
        );
        Ingredient eggs = new Ingredient("Eggs", 0, "Dairy");
        Ingredient chicken = new Ingredient("Chicken", 0, "Meat");

        catalog.addIngredient(eggs);
        catalog.addIngredient(chicken);

        ArrayList<Ingredient> result =
            catalog.getIngredientsByCategory("Dairy");

        assertEquals(1, result.size());
        assertTrue(result.contains(eggs));
        assertFalse(result.contains(chicken));
    }
}

