package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class typeActivity extends AppCompatActivity {
    public static final String ANONYME = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
    }

    public void MathActivity(View view) {
    }

    public void CultureActivity(View view) {
    }

    public void annuler(View view) {
        super.finish();
    }
}