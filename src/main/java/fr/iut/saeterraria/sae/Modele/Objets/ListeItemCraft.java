package fr.iut.saeterraria.sae.Modele.Objets;

import java.util.HashMap;

public interface ListeItemCraft {


    public Item creerItem(String nom);

    public Item creerItem(int i);

    public HashMap<Integer,Recette> listRecettes();
}