package com.hector.mycryptotracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hector.mycryptotracker.R;

public class AdditionalInfoActivity extends AppCompatActivity {

    Button buttonLink;
    String url = "https://www.youtube.com/channel/UC36xmz34q02JYaZYKrMwXng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);

        buttonLink = findViewById(R.id.buttonLink);
        buttonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri link = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, link);
                startActivity(intent);
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
                Toast.makeText(getApplicationContext(), "Ya estás en la página de información adicional", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }
}