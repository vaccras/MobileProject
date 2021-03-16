package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.project.db.Compte;


public class CompteAdapter extends ArrayAdapter<Compte> {

    public CompteAdapter(Context mCtx, List<Compte> compteList) {
        super(mCtx, R.layout.template_compte, compteList);
    }

    /**
     * Remplit une ligne de la listView avec le nom et prenom du compte
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Récupération de la multiplication
        final Compte cmpt = getItem(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_compte, parent, false);

        // Récupération des objets graphiques dans le template
        TextView textViewPrenom = (TextView) rowView.findViewById(R.id.textPrenom);
        TextView textViewNom = (TextView) rowView.findViewById(R.id.textNom);
        TextView textViewAge = (TextView) rowView.findViewById(R.id.textAge);

        //mise a jour de l'affichage
        textViewPrenom.setText(cmpt.getPrenom());
        textViewNom.setText(cmpt.getNom());
        textViewAge.setText(String.valueOf(cmpt.getAge())+"ans");

        return rowView;
    }

}
