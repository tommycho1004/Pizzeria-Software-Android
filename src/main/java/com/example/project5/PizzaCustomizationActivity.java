package com.example.project5;

import static com.example.project5.MainMenuActivity.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Pizza Customizer Activity class
 *
 * @author Tommy Cho
 */

public class PizzaCustomizationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ImageView pizzaPic;
    private Spinner sizeMenu;
    private TextView pizzaPrice;
    private ListView selectedList, addList;
    private Pizza pizza;
    private ArrayList<Topping> initialToppings, toppingsList, addListHelper, selectedListHelper;
    private ArrayAdapter<Topping> selectedAdapter, addAdapter;
    private Topping addTopping, selectTopping;

    private final int maxToppings = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_customization);
        setTitle("Pizza Customizer");
        //setting initial pizza in the class to null
        pizza = null;
        pizzaPic = findViewById(R.id.pizzaPic);
        sizeMenu = findViewById(R.id.sizeMenu);
        //set spinner listener
        sizeMenu.setOnItemSelectedListener(this);
        pizzaPrice = findViewById(R.id.pizzaPrice);
        selectedList = findViewById(R.id.selectedList);
        addList = findViewById(R.id.addList);
        //set listview listeners
        selectedList.setOnItemClickListener(this);
        addList.setOnItemClickListener(this);
        //receive intent from main menu
        Intent intent = getIntent();
        String flavor = intent.getStringExtra("FLAVOR");
        toppingsList = getAllToppings();
        initialToppings = new ArrayList<>();
        selectedListHelper = new ArrayList<>();
        addListHelper = toppingsList;
        //calls pizzaHelper to initialize the correct pizza
        pizzaHelper(flavor);
        //setting adapters
        selectedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedListHelper);
        selectedList.setAdapter(selectedAdapter);
        addAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addListHelper);
        addList.setAdapter(addAdapter);
    }

    //helper method to create pizza corresponding to flavor
    private void pizzaHelper(String flavor) {
        switch (flavor) {
            case "Pepperoni":
                pizzaPic.setImageResource(R.drawable.pepperoni);
                initialToppings.add(Topping.Pepperoni);
                pizza = PizzaMaker.createPizza(flavor);
                addListHelper.removeAll(initialToppings);
                selectedListHelper.addAll(initialToppings);
                break;
            case "Hawaiian":
                pizzaPic.setImageResource(R.drawable.hawaiian);
                initialToppings.add(Topping.Ham);
                initialToppings.add(Topping.Pineapple);
                pizza = PizzaMaker.createPizza(flavor);
                addListHelper.removeAll(initialToppings);
                selectedListHelper.addAll(initialToppings);
                break;
            case "Deluxe":
                pizzaPic.setImageResource(R.drawable.deluxe);
                initialToppings.add(Topping.Sausage);
                initialToppings.add(Topping.Peppers);
                initialToppings.add(Topping.Onions);
                initialToppings.add(Topping.Mushrooms);
                initialToppings.add(Topping.Pepperoni);
                pizza = PizzaMaker.createPizza(flavor);
                addListHelper.removeAll(initialToppings);
                selectedListHelper.addAll(initialToppings);
                break;
        }
    }

    //helper method to return an arraylist with all available toppings
    private ArrayList<Topping> getAllToppings() {
        ArrayList<Topping> toppingsList = new ArrayList<>();
        toppingsList.add(Topping.Sausage);
        toppingsList.add(Topping.Peppers);
        toppingsList.add(Topping.Onions);
        toppingsList.add(Topping.Mushrooms);
        toppingsList.add(Topping.Pepperoni);
        toppingsList.add(Topping.Ham);
        toppingsList.add(Topping.Pineapple);
        toppingsList.add(Topping.Chicken);
        return toppingsList;
    }

    //size spinner handler
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (sizeMenu.getSelectedItem().toString().equals("Small")) {
            pizza.setSize(Size.Small);
        } else if (sizeMenu.getSelectedItem().toString().equals("Medium")) {
            pizza.setSize(Size.Medium);
        } else {
            pizza.setSize(Size.Large);
        }
        pizzaPrice.setText("");
        DecimalFormat dec = new DecimalFormat("#0.00");
        dec.setGroupingUsed(true);
        dec.setGroupingSize(3);
        //update price
        pizzaPrice.setText(dec.format(pizza.price()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //add or remove topping listview handler
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.addList:
                addTopping = addAdapter.getItem(position);
                selectTopping = null; //to make sure we're only working with one list at a time
                Topping selectedTopping = addTopping;
                if (pizza.toppings.size() == maxToppings) { //max amount of toppings reached
                    Context context = getApplicationContext();
                    CharSequence text = "Maximum number of toppings is 7!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                if (selectedTopping == null) { //no topping selected
                    Context context = getApplicationContext();
                    CharSequence text = "No topping chosen to add!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                //update adapters and update listviews
                selectedAdapter.add(selectedTopping);
                addAdapter.remove(selectedTopping);
                addList.setAdapter(addAdapter);
                selectedList.setAdapter(selectedAdapter);
                //add topping to pizza
                pizza.toppings.add(selectedTopping);
                //reset addTopping
                addTopping = null;
                Context context = getApplicationContext();
                CharSequence text = "Topping added!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                pizzaPrice.setText("");
                DecimalFormat dec = new DecimalFormat("#0.00");
                dec.setGroupingUsed(true);
                dec.setGroupingSize(3);
                //update price
                pizzaPrice.setText(dec.format(pizza.price()));
                break;
            case R.id.selectedList:
                //similar case as above, see comments
                selectTopping = selectedAdapter.getItem(position);
                addTopping = null;
                selectedTopping = selectTopping;
                if (selectedTopping == null) {
                    context = getApplicationContext();
                    text = "No topping chosen!";
                    duration = Toast.LENGTH_SHORT;

                    toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                selectedAdapter.remove(selectedTopping);
                addAdapter.add(selectedTopping);
                addList.setAdapter(addAdapter);
                selectedList.setAdapter(selectedAdapter);
                pizza.toppings.remove(selectedTopping);
                selectTopping = null;
                context = getApplicationContext();
                text = "Topping removed!";
                duration = Toast.LENGTH_SHORT;

                toast = Toast.makeText(context, text, duration);
                toast.show();
                pizzaPrice.setText("");
                dec = new DecimalFormat("#0.00");
                dec.setGroupingUsed(true);
                dec.setGroupingSize(3);
                pizzaPrice.setText(dec.format(pizza.price()));
                break;
        }
    }

    //method to add the pizza to the order in main menu
    public void addToOrderButtonClick(View view) {
        order.pizzas.add(pizza);

        Context context = getApplicationContext();
        CharSequence text = "Pizza Added to Order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }
}