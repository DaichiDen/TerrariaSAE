package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.HashMap;

public abstract class ListeItemCraft {

    public ListeItemCraft() {
    }

    public abstract Item creerItem(String nom);

    public abstract Item creerItem(int i);

    public abstract HashMap<Integer,Recette> listRecettes();
}