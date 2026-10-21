/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author franc
 */
public class PantryStorage {

    private Pantry pantry;

    private static final String FILE_NAME = "pantry.txt";

    public PantryStorage(Pantry pantry) {
        this.pantry = pantry;
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
                    pantry.addIngredient(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load " + FILE_NAME + ": " + e.getMessage());
        }
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Ingredient ingredient : pantry.getIngredients()) {
                writer.println(ingredient.getName() + ":" + ingredient.getQuantity());
            }
        } catch (IOException e) {
            System.err.println("Could not save " + FILE_NAME + ": " + e.getMessage());
        }
    }
}

