package com.hector.mycryptotracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hector.mycryptotracker.R;
import com.hector.mycryptotracker.controllers.TransactionController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


        TransactionController controller = new TransactionController(getApplicationContext());

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, ListActivity.getOptions());
        adapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        spinner.setSelection(intent.getIntExtra("option",0));


        Button addTransactionButton = findViewById(R.id.addTransactionButton);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean errors = false;

                // Spinner validation
                String transactionType = (String) spinner.getSelectedItem();

                // Crypto name validation
                EditText cryptoName = (EditText) findViewById(R.id.cryptoName);
                String name = cryptoName.getText().toString().trim();

                if(name.equals("")){
                    //show validation error
                    cryptoName.setError("Introduzca el nombre de la divisa");
                    errors = true;
                }

                // Crypto value validation
                EditText cryptoValue = (EditText) findViewById(R.id.cryptoValue);

                float value = 0;
                try {
                    value = Float.parseFloat(cryptoValue.getText().toString());

                } catch (java.lang.NumberFormatException exception) {
                    cryptoValue.setError("Introduzca un valor numérico válido");
                    errors = true;
                }

                if(value == 0){
                    //show validation error
                    cryptoValue.setError("Introduzca un valor válido");
                    errors = true;
                }

                // Date validation
                EditText transactionDate = (EditText) findViewById(R.id.transactionDate);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.

                Date dateObject = new Date();
                String dateStr = "";

                try{
                    String dob_var=(transactionDate.getText().toString());

                    dateObject = formatter.parse(dob_var);
                    dateStr = new SimpleDateFormat("dd/MM/yyyy").format(dateObject);
                }

                catch (java.text.ParseException e)
                {
                    e.printStackTrace();
                    transactionDate.setError("Introduzca la fecha en formato dd/mm/aaaa");
                    errors = true;
                }

                if(dateStr.equals("")){
                    //show validation error
                    transactionDate.setError("Introduzca la fecha de la transacción");
                    errors = true;
                }

                // Crypto amount validation
                EditText cryptoAmount = findViewById(R.id.cryptoAmount);

                float amount = 0;
                try {
                    amount = Float.parseFloat(cryptoAmount.getText().toString());

                } catch (java.lang.NumberFormatException exception) {
                    cryptoAmount.setError("Introduzca un valor numérico válido");
                    errors = true;
                }

                if(value == 0){
                    //show validation error
                    cryptoAmount.setError("Introduzca un valor válido");
                    errors = true;
                }

                // Show toast with validation error
                if(errors){
                    Toast.makeText(getApplicationContext(), "Validación fallida, corrija los errores para continuar", Toast.LENGTH_SHORT).show();

                } else {
                    // Save data
                    controller.createTransaction(transactionType, name, value, dateStr, amount);

                    // Notification of creation
                    NotificationCompat.Builder notif = new NotificationCompat.Builder(AddTransactionActivity.this, String.valueOf(1));
                    notif.setContentText("Se ha añadido la " + transactionType + " de " + amount  + " en " + name +
                            " a " + value + "$").setContentTitle("Transacción añadida")
                    .setSmallIcon(R.mipmap.ic_launcher_round);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(1, notif.build());

                    // Move to list
                    Intent intent = new Intent(AddTransactionActivity.this, ListActivity.class);
                    startActivity(intent);
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
                intent = new Intent(this, ListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.activity_add_transaction:
                Toast.makeText(getApplicationContext(), "Ya estás añadiendo una nueva transacción", Toast.LENGTH_SHORT).show();
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
}