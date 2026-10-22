package model;

/**
 * Creates each ingredient and assigns it's properties
 *
 * @author franc
 */
public class Ingredient {

    private String name;
    private int quantity;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void changeQuantity(int amount) {
        this.quantity += amount;
    }
}
