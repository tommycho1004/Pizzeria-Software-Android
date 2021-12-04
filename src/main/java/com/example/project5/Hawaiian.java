package com.example.project5;

import java.lang.StringBuilder;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Hawaiian class that extends the Pizza class and holds relevant information for hawaiian pizzas.
 *
 * @author Tommy Cho, Neha Gudur
 */
public class Hawaiian extends Pizza {

    private static final double hawaiianBasePrice = 10.99;
    private static final int hawaiianBaseToppings = 2;

    /**
     *The default constructor for a hawaiian pizza
     */
    public Hawaiian(){
        this.toppings = new ArrayList<Topping>();
        toppings.add(Topping.Ham);
        toppings.add(Topping.Pineapple);
        this.size = Size.Small;
    }

    /**
     * The parameterized constructor for a hawaiian pizza
     * @param toppings topping list of pizza
     * @param size size of pizza
     */
    public Hawaiian(ArrayList<Topping> toppings, Size size){
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * The method to determine the price of a hawaiian pizza.
     *
     * @return price of the pizza in double form.
     */
    @Override
    public double price() {
        double price = hawaiianBasePrice;
        if (toppings.size() > hawaiianBaseToppings) {
            price = price + ((toppings.size() - hawaiianBaseToppings) * toppingIncrement);
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
     * The print method for an instance of hawaiian pizza.
     *
     * @return the details of this hawaiian pizza in String form
     */
    @Override
    public String toString() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        StringBuilder str = new StringBuilder();
        str.append(this.size.pizzaSizeString() + " Hawaiian Pizza - Toppings: ");
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
