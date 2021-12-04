package com.example.project5;

/**
 * The Size enum class that holds the three sizes of pizza.
 *
 * @author Tommy Cho, Neha Gudur
 */
public enum Size {
    Small, Medium, Large;

    /**
     * A method that returns the size of the pizza in String form
     *
     * @return size of Pizza in String form
     */
    public String pizzaSizeString() {
        if (this == Small) {
            return "Small";
        } else if (this == Medium) {
            return "Medium";
        } else {
            return "Large";
        }
    }
}
