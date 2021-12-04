package com.example.project5;

/**
 * The PizzaMaker class that instantiates a pizza depending on a chosen flavor
 * Flavors are: Pepperoni, Hawaiian, Deluxe (case sensitive)
 *
 * @author Tommy Cho, Neha Gudur
 */
public class PizzaMaker {

    //constants for base prices of each pizza flavor
    private static final double pepperoniBasePrice = 8.99;
    private static final double hawaiianBasePrice = 10.99;
    private static final double deluxeBasePrice = 12.99;

    /**
     * A method that instantiates a pizza.
     *
     * @param flavor flavor of pizza being created
     * @return pizza that was made
     */
    public static Pizza createPizza(String flavor){
        if (flavor.equals("Pepperoni")){
            return new Pepperoni();
        }
        else if (flavor.equals("Hawaiian")){
            return new Hawaiian();
        }
        else{
            return new Deluxe();
        }
    }
}