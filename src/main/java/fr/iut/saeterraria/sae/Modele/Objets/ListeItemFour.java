package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.HashMap;

public class ListeItemFour extends ListeItemCraft{

    public ListeItemFour() {

    }

    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Fer" :
                return new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1,22);
            case "DELJCCium" :
                return  new Item("DELJCCium", "", 1,(23));
        }
        return null;
    }

    @Override
    public Item creerItem(int i) {
        switch (i) {
            case 22 :
                return new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1,22);
            case 23 :
                return  new Item("DELJCCium", "", 1,(23));
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
