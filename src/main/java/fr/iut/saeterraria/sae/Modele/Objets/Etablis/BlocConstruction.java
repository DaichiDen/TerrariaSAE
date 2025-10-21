package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.ListeItems;
import fr.iut.saeterraria.sae.Modele.Objets.Recette;

import java.util.HashMap;

public abstract class BlocConstruction extends Bloc {
    HashMap<Integer, Recette> listeRecettes;
    private ListeItems listeConstructionsItemsPossibles;

    public BlocConstruction(String nom, String desc, int resistance, int codeobjet, ListeItems constructionsItems) {
        super(nom,desc, resistance, codeobjet);
        this.listeRecettes = new HashMap<>();
        this.listeConstructionsItemsPossibles = constructionsItems;
    }

    public HashMap<Integer, Recette> getListeRecette(){
        return this.listeRecettes;
    }
}
