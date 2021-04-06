package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.db.Compte;
import com.example.project.db.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class compteActivity extends AppCompatActivity {
    //DATA
    private DatabaseClient mDb;
    private CompteAdapter adapter;

    private ListView listTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        listTask = findViewById(R.id.listTask);

        // Lier l'adapter au listView
        adapter = new CompteAdapter(this, new ArrayList<Compte>());
        listTask.setAdapter(adapter);

        //evenement click a la liste view -> passage à la vue type
        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Récupération de la tâche cliquée à l'aide de l'adapter
                Compte cmpt = adapter.getItem(position);

                // Message
                Toast.makeText(compteActivity.this, "Click : " + cmpt.getPrenom(), Toast.LENGTH_SHORT).show();

                //affichage de la page suivante en passant les informations
                nextPage(cmpt.getPrenom(), cmpt.getNom(), view);
            }
        });

        getComptes();
    }

    private void getComptes() {
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetTasks extends AsyncTask<Void, Void, List<Compte>> {

            @Override
            protected List<Compte> doInBackground(Void... voids) {
                //recuperation de la liste de tout les comptes existants
                List<Compte> compteList = mDb.getAppDatabase().compteDao().getAll();
                return compteList;
            }

            @Override
            protected void onPostExecute(List<Compte> cmpts) {
                super.onPostExecute(cmpts);

                // Mettre à jour l'adapter avec la liste de taches
                adapter.clear();
                adapter.addAll(cmpts);

                // notifier l'adapter
                adapter.notifyDataSetChanged();
            }
        }

        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    public void CreerCompte(View view) {
        //creation d'un nouveau compte utilisateur
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void Annuler(View view) {
        //retour a la page d'accueil
        setResult(RESULT_OK);
        super.finish();
    }

    public void AnonymeActivity(View view) {
        //jouer en anonyme -> pas de mise a jour de compte de score
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.PRENOM_KEY, "anonyme");
        intent.putExtra(typeActivity.NOM_KEY, "anonyme");
        startActivity(intent);
    }

    public void nextPage(String prenom, String nom, View view){
        //jouer avec un utilisateur
        Intent intent = new Intent(this, typeActivity.class);
        intent.putExtra(typeActivity.PRENOM_KEY, prenom);
        intent.putExtra(typeActivity.NOM_KEY, nom);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Mise à jour des comptes après création
        getComptes();
    }
}