package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocCraft;

import java.util.HashMap;

public class ListeItemSansBlocCraft implements ListeItemCraft{

    public ListeItemSansBlocCraft() {

    }

    @Override
    public Item creerItem(String nom) {
        if (nom.equals("Etabli")){
            BlocCraft blocCraft = new BlocCraft();
            blocCraft.addInRecette(new ElementRecette(3,"Bois",2));
            return blocCraft;
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        if (i==12){
            BlocCraft blocCraft = new BlocCraft();
            blocCraft.addInRecette(new ElementRecette(3,"Bois",2));
            return blocCraft;
        }
        return null;
    }

    @Override
    public HashMap<Integer, Recette> listRecettes() {
        HashMap<Integer, Recette> recettes = new HashMap<>();

        recettes.put(12,creerItem(12).getAttributRecette());
        return recettes;
    }
}
