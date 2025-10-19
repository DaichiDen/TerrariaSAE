package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import javafx.beans.property.*;

public abstract class EntiteVivante extends Entite{

    private IntegerProperty energieMax, def;
    private IntegerProperty energie;
    private IntegerProperty vitesseMax;
    private BarreVie barreVie;

    private BooleanProperty estVivant;

    private boolean enSaut = false;

    private int vitesseY = 0;

    private int rangeVue; //TODO c'est quoi ?
    private int rangeAttaque;

    //constantes
    protected final int forceSaut = -18;
    //inertie et friction
    private int vitesseX = 0;



    public EntiteVivante(int vieMax, int energieMax, int energie, int x, int y, int def, int vitesseMax, int attaque, int tailleL, int tailleH, int rangeVue, int rangeAttaque) {
        super(x, y, attaque, tailleL, tailleH);

        this.barreVie = new BarreVie(vieMax);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
        this.def = new SimpleIntegerProperty(def);
        this.vitesseMax = new SimpleIntegerProperty(vitesseMax);
        this.estVivant= new SimpleBooleanProperty(true);
        this.rangeVue = rangeVue;
        this.rangeAttaque = rangeAttaque;
    }

    public int getRangeVue() {
        return rangeVue;
    }
    public int getRangeAttaque() {
        return rangeAttaque;
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

    public void testerVertical() {
        if(collisionVerticale()){
            gérerCollisionVerticale();
        }
    }
    public void gérerCollisionVerticale(){
        int blocHaut = getyBloc();
        int blocBas = getyBloc() + Jeu.getUniqueJeu().getTaille1bloc();
        int joueurHaut = getY();
        int joueurBas = getY() + (Jeu.getUniqueJeu().getTaille1bloc() * 2);
        appliquerCollisionVerticale(blocHaut,blocBas,joueurBas,joueurHaut);
        if (Carte.getUniqueCarte().getPique((joueurBas/32), (this.getX()/32)) ) {
            this.decrementVie(1);
        }
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
    }

    public void testerHorizontal() {
        if (collisionHorizontale()) {
            gérerCollisionHorizontale();
        }
    }
    public void gérerCollisionHorizontale(){
        // Bords du bloc
        int blocGauche = getxBloc();
        int blocDroite = getxBloc() + Jeu.getUniqueJeu().getTaille1bloc();
        // Bords du joueur
        int joueurGauche = this.getX();
        int joueurDroite = this.getX() + Jeu.getUniqueJeu().getTaille1bloc();
        appliquerCollisionHorizontale(blocGauche, blocDroite, joueurGauche, joueurDroite);
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


    // Gestion de l'energie
    public final IntegerProperty energieMaxProperty(){return energieMax;}
    public final int getEnergieMax() {return energieMax.get();}
    public final void setEnergieMax(int energieMax) {this.energieMax.set(energieMax);}
    public final int getEnergie() {return energie.get();}
    public final void setEnergie(int energie) {this.energie.set(energie);}


    // Gestion de la defense
    public final IntegerProperty defProperty(){ return def; }
    public final void setDef(int def) { this.def.setValue(def);}
    public final int getDef() { return def.get(); }
    public final void incrementDef(int val) {
        setDef(getDef()+val);
    }
    public final void decrementDef(int val) {
        if(getDef()-val < 0){
            setDef(0);
        }else{
            setDef(getDef()-val);
        }
    }


    // Gestion de la vitesse
    public final IntegerProperty vitesseMaxProperty(){return vitesseMax;}
    public final int getVitesseMax() {return vitesseMax.getValue();}
    public void setVitesseMax(int vitesse) {this.vitesseMax.setValue(vitesse);}

    public void resterInBounds(){
        if(this.getX()<=0){
            setX(0);
        }
        if(this.getY()<=0){
            setY(0);
        }
    }
}

