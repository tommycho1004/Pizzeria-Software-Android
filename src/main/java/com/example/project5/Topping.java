package com.example.project5;

/**
 * The Topping enum class that holds all the toppings possible for a pizza.
 *
 * @author Tommy Cho, Neha Gudur
 */

public enum Topping {
    Pepperoni, Pineapple, Ham, Sausage, Peppers, Onions, Mushrooms, Chicken;

    /**
     * To string method for the topping class
     *
     * @return topping as a String
     */
    @Override
    public String toString() {
        if (this == Pepperoni) {
            return "Pepperoni";
        } else if (this == Ham) {
            return "Ham";
        } else if (this == Sausage) {
            return "Sausage";
        } else if (this == Peppers) {
            return "Peppers";
        } else if (this == Onions) {
            return "Onions";
        } else if (this == Pineapple) {
            return "Pineapple";
        } else if (this == Mushrooms) {
            return "Mushrooms";
        } else if (this == Chicken) {
            return "Chicken";
        } else {
            return "Invalid";
        }
    }
}
