/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
public class test {

    public static void main(String[] args) throws java.io.IOException {
        IngredientReader reader = new IngredientReader();
        Catalog catalog = reader.readCatalog();

        Pantry pantry = new Pantry(catalog);

        pantry.addIngredient("Milk", 2);
        pantry.addIngredient("Eggs", 6);

        System.out.println(pantry.getQuantity("Milk")); // 2

        pantry.removeIngredient("Milk", 1);

        System.out.println(pantry.getQuantity("Milk")); // 1
    }
}

