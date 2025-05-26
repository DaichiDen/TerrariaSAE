package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Map.Map;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.HashSet;

public class Ennemi extends Entite {

    private ArrayList<Item> listDrops;

    public Ennemi(String nom, int vieMax,int energieMax, int x, int y, int def, int vitesse, Map map) {

        super(nom,vieMax,  energieMax, 20, x, y, def, vitesse,map);
        listDrops = new ArrayList<>();
    }

    public ArrayList<Item> getListDrops() {
        return this.listDrops;
    }
    public int dropItem(){
        return (int) (Math.random()*11);
    }

    public void mettreAJour(){
        super.mettreAJour();
    }

    public boolean detecterJoueur() { // À définir la distance où il détecte le joueur
        return false;
    }
}
