package com.example.project5;

import java.util.ArrayList;

/**
 * The StoreOrders class that holds a list of all orders the store has taken
 * This class can export store orders into another text file with export()
 *
 * @author Tommy Cho, Neha Gudur
 */

public class StoreOrders {
    protected ArrayList<Order> orders;

    /**
     * A default constructor for the store orders list
     */
    public StoreOrders(){
        this.orders = new ArrayList<>();
    }

//    For previous project
//    /**
//     * A method to print the orders in the list.
//     * Used for testing
//     *
//     * @return list of orders in String form
//     */
//    public String printOrders() {
//        DecimalFormat dec = new DecimalFormat("#0.00");
//        dec.setGroupingUsed(true);
//        dec.setGroupingSize(3);
//        StringBuilder str = new StringBuilder();
//        str.append("List of Orders:\n");
//        for (int i = 0; i < orders.size(); i++) {
//            str.append(orders.get(i).printOrder() + "\n");
//        }
//        return str.toString();
//    }
//
//    public void export() {
//        File myObj = new File("StoreOrders.txt");
//        try {
//            FileWriter myWriter = new FileWriter("StoreOrders.txt");
//            myWriter.write(this.printOrders());
//            myWriter.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
}
