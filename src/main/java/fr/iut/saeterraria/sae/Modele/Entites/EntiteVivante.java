package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import javafx.beans.property.*;

/*
 * Classe représentant une entité vivante du jeu (joueur, ennemi...).
 * Elle définit les propriétés communes à toutes les entités vivantes:
 * vitesse de déplacement, energie, defense, vie, la portée
 *
 * Elle donne les méthodes utilisées par toutes les entités vivantes pour la gestion de leurs collisions,
 * la gestion de leur déplacements ainsi que la gestion de leur attributs (vie, defense, energie...)
 *
 */

public abstract class EntiteVivante extends Entite{

    private Integer def;
    private Integer vitesseMax;
    private BarreVie barreVie;

    private BooleanProperty estVivant;

    private boolean enSaut = false;

    private int vitesseY = 0;

    private int porteeVue;

    //constantes
    protected final int forceSaut = -18;
    //inertie et friction
    private int vitesseX = 0;



    public EntiteVivante(int vieMax, int x, int y, int def, int vitesseMax, int attaque, int tailleL, int tailleH, int porteeVue) {
        super(x, y, attaque, tailleL, tailleH);

        this.barreVie = new BarreVie(vieMax);
        this.def = def;
        this.vitesseMax = vitesseMax;
        this.estVivant= new SimpleBooleanProperty(true);
        this.porteeVue = porteeVue;
    }

    public int getPorteeVue() {
        return porteeVue;
    }

    public int getVitesseY(){
        return vitesseY;
    }
    public int getVitesseX() { return vitesseX; }

    public void sauter() {
        if (!enSaut && this.getEstVivant() ) {
            enSaut = true;
            vitesseY = forceSaut;
        }
    }


    public abstract void action(int x, int y);



    public void setVitesseX(int val){
        this.vitesseX=val;

    }
    public void setVitesseY(int val){
        this.vitesseY=val;

    }

    public void mettreAJour() {
        if (getEstVivant()) {
            miseAJourGravité();  // Appliquer gravité

            // inertie
            boolean auSol = getCollisionBas();
            int accel = initAccel(auSol);
            int friction = initFriction(auSol);

            miseAJourVitesseHorizontale(accel, friction);  // Appliquer déplacement

        } else {
            miseAJourGravité();  // Appliquer gravité
        }
        resterInBounds();
    }

    public int initAccel(boolean auSol){
        int accel;
        if (auSol) {
            accel = getAccel_sol();
        } else {
            accel = getAccel_air();
        }
        return accel;
    }

    public int initFriction(boolean auSol){
        int friction;
        if (auSol) {
            friction = getFriction_sol();
        } else {
            friction = getFriction_air();
        }
        return friction;
    }

    public void appliquerCollisionVerticale(int blocHaut, int blocBas, int joueurBas, int joueurHaut){
        if (joueurBas >= blocHaut && vitesseY >= 0 && joueurHaut < blocHaut) {
            setCollisionBas(true);
            enSaut = false;
            vitesseY = 0;
            setY(blocHaut - (Jeu.getUniqueJeu().getTaille1bloc() * 2));
        } else if (joueurHaut <= blocBas && vitesseY < 0 && joueurBas > blocBas) {
            vitesseY = 0;
            setY(blocBas);
        }
        if (Carte.getUniqueCarte().getPique((joueurBas/32), (this.getX()/32)) ) {
            this.decrementVie(1);
        }
    }

    public void appliquerCollisionHorizontale(int blocGauche, int blocDroite, int joueurGauche, int joueurDroite){
        if (joueurDroite > blocGauche && joueurGauche < blocGauche) {
            // Collision côté droit du joueur contre gauche du bloc
            setMarcheDroite(false);
            // Repositionner le joueur pile à gauche du bloc
            this.setX(blocGauche - Jeu.getUniqueJeu().getTaille1bloc());
        } else if (joueurGauche < blocDroite && joueurDroite > blocDroite) {
            // Collision côté gauche du joueur contre droite du bloc
            setMarcheGauche(false);
            // Repositionner le joueur pile à droite du bloc
            this.setX(blocDroite);
        }
    }

    public void miseAJourGravité(){
        if (!getCollisionBas()) {
            vitesseY += getGravité();
        }
        setY(getY() + vitesseY);
        testerVertical();
    }

    public void miseAJourVitesseHorizontale(int accel, int friction){
        // Gestion de l'accélération
        if (getMarcheDroite() && !getMarcheGauche()) {
            vitesseX += accel;
        } else if (getMarcheGauche() && !getMarcheDroite()) {
            vitesseX -= accel;
        } else {
            // Si pas de touche appuyée on applique la friction
            if (vitesseX > 0) {
                vitesseX = Math.max(0, vitesseX - friction); // Réduit la vitesseX par la friction et empêche un dépassement de zéro vers le négatif.
            } else if (vitesseX < 0) {
                vitesseX = Math.min(0, vitesseX + friction); // pareil mais dans l'autre sens
            }
        }
        appliquerMouvementHorizontal();
    }

    public void appliquerMouvementHorizontal(){
        // Limiter la vitesse avec l'inertie
        if (vitesseX > getVitesseMax()) vitesseX = getVitesseMax();
        if (vitesseX < -getVitesseMax()) vitesseX = -getVitesseMax();
        // Appliquer le déplacement
        setX(getX() + vitesseX);
        testerHorizontal();
    }


    // Gestion de la vie
    public BarreVie getBarreVie(){
        return this.barreVie;
    }
    public final void incrementVie(int val) {
        if(barreVie.getVie()+val > barreVie.getVieMax()){
            barreVie.setVie(barreVie.getVie());
        }else{
            barreVie.setVie(barreVie.getVie()+val);
        }
    }

    public void decrementVie(double val) {

        if(barreVie.getVie()-val <= 0){
            barreVie.setVie(0);
            setEstVivant(false);
        }else{
            barreVie.setVie((int) (barreVie.getVie()-val));
        }
    }

    public BooleanProperty estVivantProperty() {
        return estVivant;
    }
    public boolean getEstVivant(){
        return estVivant.getValue();
    }
    public void setEstVivant(boolean estVivant){
        this.estVivant.setValue(estVivant);
    }


    // Gestion de la defense
    public final Integer getdef(){ return def; }
    public final void setDef(int def) { this.def=def;}
    public final int getDef() { return def; }


    // Gestion de la vitesse
    public final int getVitesseMax() {return vitesseMax;}
    public void setVitesseMax(int vitesse) {this.vitesseMax=vitesse;}

    public void resterInBounds(){
        if(this.getX()<=0){
            setX(0);
        }
        if(this.getY()<=0){
            setY(0);
        }
    }
}

