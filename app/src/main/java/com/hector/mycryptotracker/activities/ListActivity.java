package com.hector.mycryptotracker.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hector.mycryptotracker.R;
import com.hector.mycryptotracker.adapters.Adaptador;
import com.hector.mycryptotracker.controllers.TransactionController;
import com.hector.mycryptotracker.entities.Activity;

import java.util.ArrayList;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    public static final String[] options = new String[]{"Compra", "Venta"};

    private Activity clicked;

    public static String[] getOptions() {
        return options;
    }

    public Activity getClicked() {
        return clicked;
    }

    public void setClicked(Activity clicked) {
        this.clicked = clicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button newTransactionButton = findViewById(R.id.newTransactionButton);

        FloatingActionButton deleteTransactionButton = findViewById(R.id.deleteTransactionButton);
        deleteTransactionButton.setVisibility(View.INVISIBLE);
        deleteTransactionButton.setEnabled(false);

        ListView transactionList = findViewById(R.id.transactionList);

        TransactionController controller = new TransactionController(ListActivity.this);

        Set <Activity> transactions = controller.getTransactions();
        ArrayList transactionsArray =  new ArrayList();

        transactionsArray.addAll(transactions);

        if (transactionsArray.size() == 0) {
            Activity activity = controller.createEmptyActivity();
            transactionsArray.add(activity);
        }

        Adaptador adaptador = new Adaptador(this, transactionsArray);
        transactionList.setAdapter(adaptador);


        // Item listener
        transactionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Activity activity = (Activity) transactionsArray.get(position);
                setClicked(activity);

                // Set visibility and enable button if an item is selected
                if(activity.getType()!=null){
                    deleteTransactionButton.setVisibility(View.VISIBLE);
                    deleteTransactionButton.setEnabled(true);
                }

            }
        });

        // Add new transaction listener
        newTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsDialog(ListActivity.this);
            }
        });

        // Delete transaction listener
        deleteTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getClicked();
                if(activity!=null) {
                    controller.deleteTransaction(activity);
                    Toast.makeText(getApplicationContext(), "Transacción eliminada", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.activity_list:
                //intent = new Intent(this, ListActivity.class);
                Toast.makeText(getApplicationContext(), "Ya estás en la lista de transacciones", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_add_transaction:
                intent = new Intent(this, AddTransactionActivity.class);
                this.startActivity(intent);
                break;
            case R.id.activity_crypto_values:
                intent = new Intent(this, CryptoValuesActivity.class);
                this.startActivity(intent);
                break;
            case R.id.activity_crypto_explanation:
                intent = new Intent(this, CryptoExplanationActivity.class);
                this.startActivity(intent);
                break;
            case R.id.activity_additional_info:
                intent = new Intent(this, AdditionalInfoActivity.class);
                this.startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }


    public void showOptionsDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Elija un tipo de operación")
                .setItems(new String[]{"Compra", "Venta"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context, AddTransactionActivity.class);
                        intent.putExtra("option", which);
                        startActivity(intent);
                    }
                });
        builder.show();
    }
}