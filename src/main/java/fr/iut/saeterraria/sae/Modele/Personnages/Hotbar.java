package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

public class Hotbar {
    private int[][] hotbarJoueur;

    public Hotbar() {
        this.hotbarJoueur = new int[2][7];
    }

    public boolean ajoutHotbar(Item item, int quantite) {
        boolean trouve=false;
        int compteur=0;
        while (!trouve && compteur<7) {
            if(this.hotbarJoueur[0][compteur]==0) {
                this.hotbarJoueur[0][compteur]=item.getCodeObjet();
                this.hotbarJoueur[1][compteur]=quantite;
                trouve = true;
                System.out.println("Ajout item dans hotbar effectué");
            }
            compteur++;
        }
        if(!trouve) {
            return false;
        }
        else {
            return true;
        }
    }
}
