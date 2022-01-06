package com.example.project5;

import static com.example.project5.MainMenuActivity.storeOrders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Store Orders Activity class
 *
 * @author Tommy Cho
 */

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner phoneMenu;
    private TextView orderTotal;
    private ListView storeOrdersList;
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private ArrayAdapter<Pizza> pizzaAdapter;
    private ArrayAdapter<String> phoneAdapter;
    private int orderIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        setTitle("Store Orders");
        phoneMenu = findViewById(R.id.phoneMenu);
        orderTotal = findViewById(R.id.orderTotal);
        //no listener for listview because listview is not being changed
        storeOrdersList = findViewById(R.id.storeOrdersList);
        //set spinner listener
        phoneMenu.setOnItemSelectedListener(this);
        for (int i = 0; i < storeOrders.orders.size(); i++) {
            phoneNumbers.add(storeOrders.orders.get(i).phoneNumber);
        }
        System.out.println(phoneNumbers);
        //set adapters and update
        phoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneNumbers);
        phoneMenu.setAdapter(phoneAdapter);
        pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storeOrders.orders.get(0).pizzas);
        storeOrdersList.setAdapter(pizzaAdapter);
    }

    //method that cancels an order from the list of orders
    public void cancelOrderButtonClick(View view) {
        if (orderIndex == -1) { //no order chosen
            Context context = getApplicationContext();
            CharSequence text = "No Order Chosen!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if (storeOrders.orders.size() == 0) { //no more orders left in storeorders
            Context context = getApplicationContext();
            CharSequence text = "No More Orders!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        try{
            //remove the order
            storeOrders.orders.remove(orderIndex);
            //update phone number spinner
            phoneNumbers.remove(orderIndex);
            phoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phoneNumbers);
            phoneMenu.setAdapter(phoneAdapter);

            Context context = getApplicationContext();
            CharSequence text = "Order Cancelled!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        catch(Exception e){
            finish();
        }
    }

    //spinner handler
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        orderIndex = phoneMenu.getSelectedItemPosition();
        pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storeOrders.orders.get(orderIndex).pizzas);
        storeOrdersList.setAdapter(pizzaAdapter);
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        orderTotal.setText(dec.format(storeOrders.orders.get(orderIndex).getGrandTotal()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}