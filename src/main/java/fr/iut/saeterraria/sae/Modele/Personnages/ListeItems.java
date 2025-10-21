package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

public abstract class ListeItems {

    public ListeItems() {
    }

    public abstract Item creerItem(String nom);

    public abstract Item creerItem(int i);
}
