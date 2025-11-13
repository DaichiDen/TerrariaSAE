package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.HashMap;

public class ListeItemFour implements ListeItemCraft{

    public ListeItemFour() {

    }

    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Fer" :
                Item fer = new Item("Fer",1,22);
                fer.addInRecette(new ElementRecette(6, "Minerai Fer",1));
                fer.addInRecette(new ElementRecette(21, "Charbon",1));
                return fer;
            case "DELJCCium" :
                Item del = new Item("DELJCCium", 1,(23));
                del.addInRecette(new ElementRecette(7, "Minerai Deljccium",1));
                del.addInRecette(new ElementRecette(21, "Charbon",1));
                return del;
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        switch (i) {
            case 22 :
                return new Item("Fer",1,22);
            case 23 :
                return  new Item("DELJCCium", 1,(23));
        }
        return null;
    }

    @Override
    public HashMap<Integer, Recette> listRecettes() {
        HashMap<Integer,Recette> recettes = new HashMap<>();

        recettes.put(22,creerItem(22).getAttributRecette());
        recettes.put(23,creerItem(23).getAttributRecette());

        return recettes;
    }
}
