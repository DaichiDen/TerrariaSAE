package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import fr.iut.saeterraria.sae.Modele.Map.Map;



public class Projectile extends EntiteNonVivante{
    private FloatProperty x= new SimpleFloatProperty(0);
    private FloatProperty y = new SimpleFloatProperty(0);
    private Joueur joueur;
    private StringProperty nom;
    private double gravité = 0.5;
    private float forceX = 0, forceY = 0;
    private int xBloc, yBloc;
    private boolean collisionBas;
    private Map map;

    public Projectile(Joueur joueur, String nom, Map map, Jeu jeu, int xJoueur, int yJoueur) {
        super(nom, xJoueur, yJoueur, map, jeu);
        this.joueur = joueur;
        this.nom = new SimpleStringProperty(nom);
        this.x.setValue(joueur.getX());
        this.y.setValue(joueur.getY());
        this.map = map;
    }

    public int getxBloc() {
        return xBloc;
    }
    public int getyBloc() {
        return yBloc;
    }
    public void setxBloc(int xBloc) {
        this.xBloc = xBloc;
    }
    public void setyBloc(int yBloc) {
        this.yBloc = yBloc;
    }

    public String getNom() {
        return nom.get();
    }

    public void setX(float x) {
        this.x.setValue(x);
    }
    public void setY(float y) {
        this.y.setValue(y);
    }

    public float getForceX() {
        return forceX;
    }
    public float getForceY() {
        return forceY;
    }
    public void setForceX(float forceX) {
        this.forceX = forceX;
    }
    public void setForceY(float forceY) {
        this.forceY = forceY;
    }

    public boolean cocoVerti(int tailleL,int tailleH) { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;

        Rectangle2D hitboxEntite = new Rectangle2D(getX(), getY(), tailleL, tailleH);

        int caseX = (int) (getX() / getJeu().getTaille1bloc());
        int caseY = (int) (getY() / getJeu().getTaille1bloc());
        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < this.map.getLigne() && j >= 0 && j < this.map.getColonne()) {
                    if (this.map.getCase(i, j) != 0) { // si le bloc n'est pas du ciel
                        xBloc = this.map.getCoordonnéesX(j);
                        yBloc = this.map.getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, getJeu().getTaille1bloc(), getJeu().getTaille1bloc()); // création d'un rectangle de hitbox pour le bloc en cours
                        if (hitboxEntite.intersects(hitboxBloc)) { // si le rectangle du joueur se superpose au carré du bloc alors :
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }



}
