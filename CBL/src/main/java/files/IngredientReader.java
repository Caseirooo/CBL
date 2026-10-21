package files;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
import model.Catalog;
import model.Ingredient;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IngredientReader {

    public Catalog readCatalog() throws IOException {

        ArrayList<String> categoryNames = new ArrayList<>();
        ArrayList<Ingredient> loadedIngredients = new ArrayList<>();

        try (BufferedReader reader
                = new BufferedReader(new FileReader("catalog.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(":", 2);

                if (parts.length != 2) {
                    continue;
                }

                String category = parts[0].trim();

                if (!categoryNames.contains(category)) {
                    categoryNames.add(category);
                }

                String[] ingredientNames = parts[1].split(",");

                for (String ingredientName : ingredientNames) {
                    ingredientName = ingredientName.trim();

                    if (!ingredientName.isEmpty()) {
                        Ingredient ingredient
                                = new Ingredient(ingredientName, 0, category);

                        loadedIngredients.add(ingredient);
                    }
                }
            }
        }

        String[] categories = categoryNames.toArray(new String[0]);

        Catalog catalog = new Catalog(categories);

        for (Ingredient ingredient : loadedIngredients) {
            catalog.addIngredient(ingredient);
        }

        return catalog;
    }

    public static void main(String[] args) throws IOException {

        IngredientReader reader = new IngredientReader();
        Catalog catalog = reader.readCatalog();

        for (Ingredient ingredient : catalog.getIngredients()) {
            System.out.println(
                    ingredient.getCategory() + ": "
                    + ingredient.getName()
            );
        }
    }
}
