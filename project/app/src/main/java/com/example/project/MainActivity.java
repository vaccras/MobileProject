package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final static int PLAY_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jouer(View view) {
        Intent intent = new Intent(this, compteActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, PLAY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_REQUEST) {
            if (resultCode == RESULT_OK) {
                //from activity
                //super.finish();
            } else if (resultCode == RESULT_CANCELED) {
                //bouton back
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //permet d'eviter l'empilement
                startActivity(intent);
            }
        }
    }

    public void quitter(View view) {
        finish();
    }
}