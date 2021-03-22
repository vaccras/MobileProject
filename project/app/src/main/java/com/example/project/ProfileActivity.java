package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;

public class ProfileActivity extends AppCompatActivity {
    public static final String PRENOM_KEY = "PRENOM";
    public static final String NOM_KEY = "NOM";

    private DatabaseClient mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String prenom = getIntent().getStringExtra(PRENOM_KEY);
        String nom = getIntent().getStringExtra(NOM_KEY);

        TextView prenomView = findViewById(R.id.textPrenom);
        prenomView.setText("Profil " + prenom);

        //recuperation des objects dans le template
        TextView textViewCalcul = findViewById(R.id.txtCalcul);
        TextView textViewGeometrie = findViewById(R.id.txtGeom);
        TextView textViewCulture = findViewById(R.id.txtCulture);

        // Get the ViewModel.
        //faire une AsyncTask
        /*
        model = new ViewModelProvider(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                textViewCalcul.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getCurrentName().observe(this, nameObserver);
        */

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        final Compte cmpt = mDb.getAppDatabase().compteDao().findByName(prenom, nom);



        //mise a jour de l'affichage
        textViewCalcul.setText(String.valueOf(cmpt.getCalcul()));
        textViewGeometrie.setText(String.valueOf(cmpt.getGeometrie()));
        textViewGeometrie.setText(String.valueOf(cmpt.getCulture()));
    }

    public void retour(View view) {
        super.finish();
    }
}