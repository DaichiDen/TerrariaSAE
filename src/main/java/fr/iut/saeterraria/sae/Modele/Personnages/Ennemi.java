package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;

import java.util.ArrayList;

public class Ennemi extends Entite {

    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vie, int vieMax,int energieMax, int x, int y, int def, int vitesse) {
        super(nom,vie, vieMax, energieMax,0, x, y, def, vitesse);
        listDrops = new ArrayList<>();
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }

    public boolean detecterJoueur() { // À définir la distance où il détecte le joueur
        return false;
    }
}