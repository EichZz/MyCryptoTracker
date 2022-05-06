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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hector.mycryptotracker.R;

public class CryptoExplanationActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_explanation);

        videoView = (VideoView) findViewById(R.id.videoView_video);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videocryptos);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.start();

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
                Toast.makeText(getApplicationContext(), "Ya estás en la explicación de las criptomonedas", Toast.LENGTH_SHORT).show();
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