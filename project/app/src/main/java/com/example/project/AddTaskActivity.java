package com.example.project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;
import com.example.project.db.Compte;

public class AddTaskActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText editTextPrenom;
    private EditText editTextNom;
    private EditText editTextAge;

    private EditText editTextFinishByView;
    private Button creer;
    private Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextPrenom = findViewById(R.id.editPrenom);
        editTextNom = findViewById(R.id.editNom);
        editTextAge = findViewById(R.id.editAge);

        creer = findViewById(R.id.creer);
        annuler = findViewById(R.id.annuler);

        // Associer un événement au bouton save
        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCompte();
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annuler();
            }
        });
    }

    private void saveCompte() {

        // Récupérer les informations contenues dans les vues
        final String sPrenom = editTextPrenom.getText().toString().trim();
        final String sNom = editTextNom.getText().toString().trim();

        final String sAge = String.valueOf(editTextAge.getText().toString());

        // Vérifier les informations fournies par l'utilisateur
        if (sAge.isEmpty()) {
            editTextPrenom.setError("Age necessaire");
            editTextPrenom.requestFocus();
            return;
        }

        if (Integer.parseInt(sAge) <= 0) {
            editTextAge.setError("Age inferieur à 0");
            editTextAge.requestFocus();
            return;
        }

        if (sPrenom.isEmpty()) {
            editTextPrenom.setError("Prenom necessaire");
            editTextPrenom.requestFocus();
            return;
        }

        if (sNom.isEmpty()) {
            editTextNom.setError("Nom necessaire");
            editTextNom.requestFocus();
            return;
        }

        /**
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveCompte extends AsyncTask<Void, Void, Compte> {

            @Override
            protected Compte doInBackground(Void... voids) {

                // creating a task
                Compte cmpt = new Compte();

                cmpt.setPrenom(sPrenom);
                cmpt.setNom(sNom);
                cmpt.setAge(Integer.parseInt(sAge));

                // adding to database
                mDb.getAppDatabase().compteDao().insert(cmpt);

                return cmpt;
            }

            @Override
            protected void onPostExecute(Compte cmpt) {
                super.onPostExecute(cmpt);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        // IMPORTANT bien penser à executer la demande asynchrone
        SaveCompte sc = new SaveCompte();
        sc.execute();
    }

    private void annuler() {
        super.finish();
    }
}
