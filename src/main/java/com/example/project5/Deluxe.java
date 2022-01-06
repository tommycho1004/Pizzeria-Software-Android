package com.example.project5;

import java.lang.StringBuilder;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Deluxe class that extends the Pizza class and holds relevant information for deluxe pizzas.
 *
 * @author Tommy Cho
 */
public class Deluxe extends Pizza {

    private static final double deluxeBasePrice = 12.99;
    private static final int deluxeBaseToppings = 5;

    /**
     *The default constructor for a deluxe pizza
     */
    public Deluxe(){
        this.toppings = new ArrayList<Topping>();
        toppings.add(Topping.Sausage);
        toppings.add(Topping.Peppers);
        toppings.add(Topping.Onions);
        toppings.add(Topping.Mushrooms);
        toppings.add(Topping.Pepperoni);
        this.size = Size.Small;
    }

    /**
     * The parameterized constructor for a deluxe pizza
     * @param toppings topping list of pizza
     * @param size size of pizza
     */
    public Deluxe(ArrayList<Topping> toppings, Size size){
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * The method to determine the price of a deluxe pizza.
     *
     * @return price of the pizza in double form.
     */
    @Override
    public double price() {
        double price = deluxeBasePrice;
        if (toppings.size() > deluxeBaseToppings) {
            price = price + ((toppings.size() - deluxeBaseToppings) * toppingIncrement);
        }
        if (this.size == Size.Medium) {
            price = price + sizeIncrement;
        }
        if (this.size == Size.Large) {
            price = price + sizeIncrement + sizeIncrement;
        }
        return price;
    }

    /**
     * The print method for an instance of deluxe pizza.
     *
     * @return the details of this deluxe pizza in String form
     */
    @Override
    public String toString() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        StringBuilder str = new StringBuilder();
        str.append(this.size.pizzaSizeString() + " Deluxe Pizza - Toppings: ");
        if (toppings.isEmpty()) {
            str.append("None");
        } else {
            str.append(toppings.get(0));
            for (int i = 1; i < toppings.size(); i++) {
                str.append(", " + toppings.get(i));
            }
        }
        str.append(" - Subtotal: $" + dec.format(this.price()));
        return str.toString();
    }

    @Override
    public Pizza copy() {
        Pizza copy = new Pepperoni(new ArrayList<Topping>(this.toppings), this.size);
        return copy;
    }
}
