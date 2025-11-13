package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.beans.property.*;

/*
 * Classe représentant une entité non vivante du jeu (projectile...).
 * Elle définit les propriétés communes à toutes les entités non vivantes:
 * déplacement, vitesse de déplacement
 *
 * Elle donne les méthodes utilisées par toutes les entités non vivantes pour la gestion de leurs collisions et
 * la gestion de leur déplacement
 *
 */

public abstract class Projectile extends Entite{
    private Double forceX = 0.0;
    private Double forceY = 0.0;
    private int xBloc, yBloc;
    private BooleanProperty actif;

    public Projectile(int xJoueur, int yJoueur, int attaque, int tailleL, int tailleH) {
        super(xJoueur, yJoueur, attaque, tailleL, tailleH);
        this.actif = new SimpleBooleanProperty(true);
    }

    public abstract int getType();

    public BooleanProperty getActifProperty() {
        return actif;
    }
    public void setActif(boolean actif) {
        this.actif.set(actif);
    }

    public int getxBloc() {
        return xBloc;
    }
    public int getyBloc() {
        return yBloc;
    }

    public double getForceX() {return forceX;}

    public double getForceY() {
        return forceY;
    }

    public void setForceX(double forceX) {
        this.forceX=forceX;
    }
    public void setForceY(double forceY) {
        this.forceY=forceY;
    }

    public void màjProjectile(){
        this.setX(this.getX() + (int) this.getForceX());
        this.setY(this.getY() + (int) this.getForceY());
    }

    @Override
    public void appliquerCollisionVerticale(int blocHaut, int blocBas, int entiteBas, int entiteHaut){ // Oui, on n'utilise aucun paramètre, c'est pas bien, à modifier (template)
        this.action();
        this.setActif(false);
    }

    @Override
    public void appliquerCollisionHorizontale(int blocGauche, int blocDroite, int entiteGauche, int entiteDroite){
        this.action();
        this.setActif(false);
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
