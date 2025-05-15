package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class Ennemi extends Entite {

    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vie, int vieMax,int energieMax, int x, int y, int def, int vitesse) {
        super(nom,vie, vieMax, energieMax, 20, x, y, def, vitesse,new Rectangle2D(0,0,32,32));
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