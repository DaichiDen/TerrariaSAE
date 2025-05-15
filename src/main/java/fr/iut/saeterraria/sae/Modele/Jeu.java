package fr.iut.saeterraria.sae.Modele;

import fr.iut.saeterraria.sae.Modele.Objets.*;
import fr.iut.saeterraria.sae.Modele.Personnages.*;
import fr.iut.saeterraria.sae.Modele.Map.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

// Environnement du jeu
public class Jeu {

    private Map carte;
    private Joueur joueur;
    private ArrayList<Ennemi> mobs;
    private ArrayList<PNJ> pNJ;
    private HashMap<Integer, Image> items;

    public Jeu(String nomJoueur, int width, int height){
        carte = new Map();
        joueur = new Joueur(nomJoueur);
        mobs = new ArrayList<>();
        pNJ = new ArrayList<>();
    }

    public void addMobs(Ennemi ennemi) {
        mobs.add(ennemi);
    }
    public void removemob(Ennemi ennemi){
        mobs.remove(ennemi);
    }

    public void addPNJ(PNJ pnj) {
        pNJ.add(pnj);
    }
    public void removePNJ(PNJ pnj) {
        pNJ.remove(pnj);
    }

    public boolean estVivant(Entite entite){
        return entite.getVie()>0;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Ennemi> getMobs() {
        return mobs;
    }

    public ArrayList<PNJ> getpNJ() {
        return pNJ;
    }
}
