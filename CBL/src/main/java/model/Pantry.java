package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author franc
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pantry {

    private static final String FILE_NAME = "pantry.txt";
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

    public boolean useUp(java.util.List<Ingredient> needed) {
        for (Ingredient item : needed) {
            if (getQuantity(item.getName()) < item.getQuantity()) {
                return false;
            }
        }
        for (Ingredient item : needed) {
            removeIngredient(item.getName(), item.getQuantity());
        }
        save();
        return true;
    }
    public void load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length != 2) {
                    continue;
                }
                try {
                    addIngredient(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load " + FILE_NAME + ": " + e.getMessage());
        }
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Ingredient ingredient : ingredients) {
                writer.println(ingredient.getName() + ":" + ingredient.getQuantity());
            }
        } catch (IOException e) {
            System.err.println("Could not save " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
