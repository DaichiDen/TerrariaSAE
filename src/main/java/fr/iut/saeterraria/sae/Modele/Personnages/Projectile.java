package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import fr.iut.saeterraria.sae.Modele.Map.Map;

public class Projectile extends Entite{
    private StringProperty nom;
    private int gravité = 1;
    private int forceX = 0, forceY = 0;
    private int xBloc, yBloc;
    private BooleanProperty actif;
    private String type;


    public Projectile(String nom, Jeu jeu, int xJoueur, int yJoueur, int attaque,String type, int tailleL, int tailleH) {
        super(nom, xJoueur, yJoueur, jeu, attaque, tailleL, tailleH);
        this.nom = new SimpleStringProperty(nom);
        this.actif = new SimpleBooleanProperty(true);
        this.type=type;

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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
