package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.beans.property.*;

public abstract class Projectile extends Entite{
    private StringProperty nom;
    private DoubleProperty forceX = new SimpleDoubleProperty(0), forceY = new SimpleDoubleProperty(0);
    private int xBloc, yBloc;
    private BooleanProperty actif;

    public Projectile(int xJoueur, int yJoueur, int attaque, int tailleL, int tailleH) {
        super(xJoueur, yJoueur, attaque, tailleL, tailleH);
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

    public String getNom() {
        return nom.get();
    }

    public DoubleProperty forceXProperty() {
        return forceX;
    }
    public DoubleProperty forceYProperty() {
        return forceY;
    }
    public double getForceX() {return forceX.getValue();}
    public double getForceY() {
        return forceY.getValue();
    }

    public void setForceX(double forceX) {
        this.forceX.setValue(forceX);
    }
    public void setForceY(double forceY) {
        this.forceY.setValue(forceY);
    }


    //TODO mettre des limites à la balle à babar pour timestop
    public void màjProjectile(){
        this.setX(this.getX() + (int) this.getForceX());
        this.setY(this.getY() + (int) this.getForceY());
    }


    public void initialiserProjectile(int cibleX, int cibleY) {
        // Position de l'entité
        int ex = this.getX();
        int ey = this.getY();

        // Direction du tire
        int dx = cibleX - ex;
        int dy = cibleY - ey;

        // Normalisation du vecteur (dx, dy)
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) distance = 1; // éviter division par zéro

        // Vitesse initiale (puissance du tir)
        int puissance = 55;

        int vx = (int) (((float) dx / distance) * puissance);
        int vy = (int) (((float) dy / distance) * puissance);

        ajouterProjectile(vx, vy, ex, ey);
    }
    public void ajouterProjectile(int vx, int vy, int ex, int ey){
        // Appliquer la vitesse initiale au projectile
        this.setForceX(vx);
        this.setForceY(vy);

        // Position de départ = entité
        if(vx < 0){
            this.setX(ex);
        }else{
            this.setX(ex+32);

        }
        this.setY(ey);

        // Ajouter aux listes
        Jeu.getUniqueJeu().getListe_projectiles().add(this);
        Jeu.getUniqueJeu().getListe_projectilesObservable().add(this);
    }
    public abstract void action();

    public boolean affecteTemps(){
        return false;
    }
}
