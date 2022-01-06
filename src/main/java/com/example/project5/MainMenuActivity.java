package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Main Menu Activity class
 * Project Made on Android API 30 and tested on Pixel 3 AVD
 *
 * @author Tommy Cho
 */
public class MainMenuActivity extends AppCompatActivity {

    private EditText numberEnter;

    //static variables to be used by all controller classes
    static Order order = new Order();
    static StoreOrders storeOrders = new StoreOrders();

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Menu");
        numberEnter = findViewById(R.id.numberEnter);
        order = new Order();
        storeOrders = new StoreOrders();
    }

    //helper to check validity of a phone number
    public static boolean phoneNumberChecker(String phoneNumber) {
        try {
            Long.parseLong(phoneNumber);
            return phoneNumber.length() == 10; //phone numbers must be 10 digits long
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //pepperoni button onClick handler
    public void loadPepperoni(View view) {
        if (phoneNumberChecker(numberEnter.getText().toString())) {
            Context context = getApplicationContext();
            CharSequence text = "Adding Pepperoni!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("FLAVOR", "Pepperoni");
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid phone number!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    //hawaiian button onClick handler
    public void loadHawaiian(View view) {
        if (phoneNumberChecker(numberEnter.getText().toString())) {
            Context context = getApplicationContext();
            CharSequence text = "Adding Hawaiian!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("FLAVOR", "Hawaiian");
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid phone number!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    //deluxe button onClick handler
    public void loadDeluxe(View view) {
        if (phoneNumberChecker(numberEnter.getText().toString())) {
            Context context = getApplicationContext();
            CharSequence text = "Adding Deluxe!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("FLAVOR", "Deluxe");
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Invalid phone number!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    //check current order button onClick handler
    public void loadCurrentOrder(View view) {
        if (order.pizzas.size() != 0) {
            Context context = getApplicationContext();
            CharSequence text = "Checking Current Order!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            order.phoneNumber = numberEnter.getText().toString();

            Intent intent = new Intent(this, CurrentOrderActivity.class);
            intent.putExtra("PHONENUMBER", order.phoneNumber); //send phone number
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "No pizzas in the order!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    //load store orders button onClick handler
    public void loadStoreOrders(View view) {
        if (storeOrders.orders.size() != 0) {
            Context context = getApplicationContext();
            CharSequence text = "Loading Store Orders!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, StoreOrdersActivity.class);
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "No orders placed yet!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}