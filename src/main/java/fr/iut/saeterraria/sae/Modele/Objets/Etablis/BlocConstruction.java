package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Objets.*;

import java.util.HashMap;

public abstract class BlocConstruction extends Bloc {
    private ListeItemCraft listeConstructionsItemsPossibles;

    public BlocConstruction(String nom, String desc, int resistance, int codeobjet, ListeItemCraft constructionsItems) {
        super(nom,desc, resistance, codeobjet);
        this.listeConstructionsItemsPossibles = constructionsItems;
    }

    public Item creerItem(String nom) {
        return listeConstructionsItemsPossibles.creerItem(nom);
    }

    public Item creerItem(int id) {
        return listeConstructionsItemsPossibles.creerItem(id);
    }

    public HashMap<Integer,Recette> getListeRecettes() {
        return listeConstructionsItemsPossibles.listRecettes();
    }
}
