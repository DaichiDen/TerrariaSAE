package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import fr.iut.saeterraria.sae.Modele.Map.Map;

public class Projectile extends Entite{
    private Joueur joueur;
    private StringProperty nom;
    private int gravité = 1;
    private int forceX = 0, forceY = 0;
    private int xBloc, yBloc;
    private Map map;
    private BooleanProperty actif;

    public Projectile(Joueur joueur, String nom, Map map, Jeu jeu, int xJoueur, int yJoueur) {
        super(nom, xJoueur, yJoueur, map, jeu);
        this.joueur = joueur;
        this.nom = new SimpleStringProperty(nom);
        this.map = map;
        this.actif = new SimpleBooleanProperty(true);

    }

    public BooleanProperty getActifProperty() {
        return actif;
    }
    public void setActif(boolean actif) {
        this.actif.set(actif);
    }
    public boolean getActif() {
        return actif.getValue();
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

    @Override
    public int getGravité() {
        return gravité;
    }

    public String getNom() {
        return nom.get();
    }

    public int getForceX() {return forceX;}
    public int getForceY() {
        return forceY;
    }
    public void setForceX(int forceX) {
        this.forceX = forceX;
    }
    public void setForceY(int forceY) {
        this.forceY = forceY;
    }

}
