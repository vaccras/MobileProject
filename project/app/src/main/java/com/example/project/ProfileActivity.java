package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;

import java.util.List;

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

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        //faire une AsyncTask
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetCompte extends AsyncTask<Void, Void, Compte> {

            @Override
            protected Compte doInBackground(Void... voids) {
                Compte profile = mDb.getAppDatabase().compteDao().findByName(prenom, nom);
                return profile;
            }

            @Override
            protected void onPostExecute(Compte profile) {
                super.onPostExecute(profile);

                //mise a jour de l'affichage
                textViewCalcul.setText(String.valueOf(profile.getCalcul()));
                textViewGeometrie.setText(String.valueOf(profile.getGeometrie()));
                textViewCulture.setText(String.valueOf(profile.getCulture()));
            }
        }

        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetCompte gc = new GetCompte();
        gc.execute();
    }

    public void retour(View view) {
        super.finish();
    }

    public void supprime(View view) {
        String prenom = getIntent().getStringExtra(PRENOM_KEY);
        String nom = getIntent().getStringExtra(NOM_KEY);

        class DeleteCompte extends AsyncTask<Void, Void, Boolean> {

            @Override
            protected Boolean doInBackground(Void... voids) {
                Compte profile = mDb.getAppDatabase().compteDao().findByName(prenom, nom);
                // delete to database
                mDb.getAppDatabase().compteDao().delete(profile);
                return true;

            }

            @Override
            protected void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
            }
        }

        // IMPORTANT bien penser à executer la demande asynchrone
        DeleteCompte sc = new DeleteCompte();
        sc.execute();

    }
}