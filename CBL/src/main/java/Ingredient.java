/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author franc
 */
public class Ingredient {

    private String name;
    private int quantity;
    private String category;
    int n; //quantity to be changed

    public Ingredient(String name, int quantity, String category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;

    }

    public String getName() {

        return name;
    }
    
    public int getQuantity() {

        return quantity;
    }
    public void changeQuantity(int n){
        this.quantity = quantity - n;
    }
    
    public String getCategory() {

        return category;
    }
}
