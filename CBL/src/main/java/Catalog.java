/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
import java.util.ArrayList;

public class Catalog {
    
    private String[] categories;
    private ArrayList<Ingredient> ingredients; // this is an array that can grow as we add new ingredients

    public Catalog(String[] categories){
        this.categories = categories;
    }

}
