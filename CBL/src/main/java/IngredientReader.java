/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class IngredientReader {

    public void readCatalog() throws Exception { //ignores the possibility of the file being deleted
        BufferedReader reader = new BufferedReader(new FileReader("catalog.txt"));
        String line;
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(":"); //sperate parts on the :, parts[0] = cat 1 ingredients
            String[] ingredientNames = parts[1].split(","); //seperates again on the ingridients this time

            for (String ingredientName : ingredientNames) { // temp call ingridientNames[n] ingredientName
                ingredientName = ingredientName.trim();
                Ingredient ingredient = new Ingredient(ingredientName, 0, parts[0]);
                //System.out.println(ingredientName); // test if ingredients are printing right
            }
//            for (String n : parts){
//                System.out.println(n); test what is in parts 
//            }
        }
    }

    public static void main(String[] args) throws Exception {
        IngredientReader storage = new IngredientReader();
        storage.readCatalog();
    }                                 //TEST MEHTOD
}
