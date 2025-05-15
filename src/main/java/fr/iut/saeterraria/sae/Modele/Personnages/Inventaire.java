package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

public class Inventaire {
    private Item[][] inventaireJoueur;

    public Inventaire() {
        this.inventaireJoueur = new Item[2][56];
    }
}
