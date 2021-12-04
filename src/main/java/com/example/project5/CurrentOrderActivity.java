package com.example.project5;

import static com.example.project5.MainMenuActivity.order;
import static com.example.project5.MainMenuActivity.storeOrders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Current Order Activity class
 *
 * @author Tommy Cho
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView number, salesTax, orderTotalCO, subtotal;
    ListView pizzaList;
    Button removePizza, addOrder;
    Pizza pizza = null;
    ArrayAdapter<Pizza> pizzaAdapter;

    //On Create method to start the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        setTitle("Current Order");
        number = findViewById(R.id.number);
        pizzaList = findViewById(R.id.pizzaList);
        pizzaList.setOnItemClickListener(this);
        removePizza = findViewById(R.id.removePizza);
        addOrder = findViewById(R.id.addOrder);
        salesTax = findViewById(R.id.salesTax);
        orderTotalCO = findViewById(R.id.orderTotalCO);
        subtotal = findViewById(R.id.subtotal);
        //get phone number from Main Menu
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("PHONENUMBER");
        number.setText(phoneNumber);
        //Decimal Formatter for Currency Format
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        salesTax.setText(dec.format(order.getTax()));
        subtotal.setText(dec.format(+order.getOrderTotal()));
        orderTotalCO.setText(dec.format(order.getGrandTotal()));
        //Pizza Adapter for the list of pizzas
        pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order.pizzas);
        pizzaList.setAdapter(pizzaAdapter);
    }

    //selects a pizza on the list to be removed, but does not remove it yet
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pizza = pizzaAdapter.getItem(position);
    }

    //method that adds the order to storeOrders
    public void addOrderClick(View view) {
        if (order.pizzas.size() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Add a pizza to the order first!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        order.phoneNumber = number.getText().toString();
        order.orderTotal = Double.parseDouble(orderTotalCO.getText().toString());
        storeOrders.orders.add(order);
        //reset order for the next order
        order = new Order();
        finish();
    }

    //method that removes pizza from the current order
    public void removePizzaClick(View view) {
        if (pizza == null) {
            Context context = getApplicationContext();
            CharSequence text = "Select a pizza to remove!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        order.pizzas.remove(pizza);
        pizzaAdapter.remove(pizza);
        //reset pizza within the class so it can be used again
        pizza = null;
        pizzaList.setAdapter(pizzaAdapter);
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        //update prices
        salesTax.setText(dec.format(order.getTax()));
        subtotal.setText(dec.format(order.getOrderTotal()));
        orderTotalCO.setText(dec.format(order.getGrandTotal()));
    }
}