package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public abstract class EntiteVivante extends Entite{

    private IntegerProperty energieMax, def;
    private IntegerProperty energie;
    private IntegerProperty vitesseMax;
    private BarreVie barreVie;

    private BooleanProperty estVivant;


    private boolean enSaut = false;

    private int vitesseY = 0;

    //constantes
    protected final int forceSaut = -18;
    //inertie et friction
    private int vitesseX = 0;



    public EntiteVivante(String nom, int vieMax, int energieMax, int energie, int x, int y, int def, int vitesseMax, Map map, Jeu jeu) {
        super(nom, x, y, map, jeu);

        this.barreVie = new BarreVie(vieMax);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
        this.def = new SimpleIntegerProperty(def);
        this.vitesseMax = new SimpleIntegerProperty(vitesseMax);
        this.estVivant= new SimpleBooleanProperty(true);

    }




    public int getVitesseY(){
        return vitesseY;
    }



    public void sauter() {
        if (!enSaut && this.estVivant() ) {
            enSaut = true;
            vitesseY = forceSaut;
        }
    }


    public abstract void attaquer(int x, int y, int range);

    public void bloquéVertical(int tailleL, int tailleH) {
        if(collisionVerticale(tailleL, tailleH)){
            int blocHaut = getyBloc();
            int blocBas = getyBloc() + getJeu().getTaille1bloc();
            int joueurHaut = getY();
            int joueurBas = getY() + (getJeu().getTaille1bloc() * 2);

            if (joueurBas >= blocHaut && vitesseY >= 0 && joueurHaut < blocHaut) { //vitesseY >=0 vérifie si le joueur est entrain de tomber
                setCollisionBas(true);
                enSaut = false;
                vitesseY = 0;
                setY(blocHaut - (getJeu().getTaille1bloc() * 2));
            } else if (joueurHaut <= blocBas && vitesseY < 0 && joueurBas > blocBas) {
                vitesseY = 0;
                setY(blocBas);
            }
            if (super.getJeu().getCarte().getCase((joueurBas/32), (this.getX()/32)) == 4 ) {
                System.out.println("true");
                this.decrementVie(1);
            }
        }
    }
    public void bloquéHorizontal(int tailleL,int tailleH) {
        boolean collisionDroite = false;
        boolean collisionGauche = false;

        if (collisionHorizontale(tailleL, tailleH)) {
            // Bords du bloc
            int blocGauche = getxBloc();
            int blocDroite = getxBloc() + super.getJeu().getTaille1bloc();
            // Bords du joueur
            int joueurGauche = this.getX();
            int joueurDroite = this.getX() + super.getJeu().getTaille1bloc();

            if (joueurDroite > blocGauche && joueurGauche < blocGauche) {
                // Collision côté droit du joueur contre gauche du bloc
                collisionDroite = true;
                // Repositionner le joueur pile à gauche du bloc
                this.setX(blocGauche - super.getJeu().getTaille1bloc());
            } else if (joueurGauche < blocDroite && joueurDroite > blocDroite) {
                // Collision côté gauche du joueur contre droite du bloc
                collisionGauche = true;
                // Repositionner le joueur pile à droite du bloc
                this.setX(blocDroite);
            }




        }
        if (collisionDroite) setMarcheDroite(false);
        if (collisionGauche) setMarcheGauche(false);
    }


    public void tirerProjectile(Projectile projectile, int cibleX, int cibleY) {
        // Position de l'entité
        int ex = this.getX();
        int ey = this.getY();

        // Direction du tire
        float dx = cibleX - ex;
        float dy = cibleY - ey;

        // Normalisation du vecteur (dx, dy)
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) distance = 1; // éviter division par zéro

        // Vitesse initiale (puissance du tir)
        float puissance = 20.0f;

        float vx = (dx / distance) * puissance;
        float vy = (dy / distance) * puissance;


        // Appliquer la vitesse initiale au projectile
        projectile.setForceX(vx);
        projectile.setForceY(vy);

        // Position de départ = entité
        projectile.setX(ex);
        projectile.setY(ey);

        // Ajouter aux listes
        projectile.setInd(getJeu().getListe_projectiles().size());
        getJeu().getListe_projectiles().add(projectile);
        getJeu().getListe_projectilesObservable().add(projectile);
    }


    public boolean peutEtreAtteint(int blocX, int blocY,double val) {
        int joueurX = (this.getX() + 16) / 32;
        int joueurY = (this.getY() + 16) / 32;

        int dx = blocX - joueurX;
        int dy = blocY - joueurY;

        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > val) return false; // Quand c'est pas à portée

        // On marche dans la ligne du joueur au bloc cible
        int rayonLaser = (Math.max(Math.abs(dx), Math.abs(dy)) * 2); // le nombre d'étapes
        for (int i = 1; i < rayonLaser; i++) {
            double t = i / (double)rayonLaser;
            int xi = (int)Math.round(joueurX + dx * t);
            int yi = (int)Math.round(joueurY + dy * t);

            if ((xi != blocX || yi != blocY) && getMap().getCase(yi, xi) != 0) { // Si bloc devant (obstacle)
                return false;
            }
        }

        return true;
    }
    public void setVitesseX(int val){
        this.vitesseX=val;

    }

    public void mettreAJour() {
        if (estVivant()) {
            if (!getCollisionBas()) {
                vitesseY += getGravité();
            }
            setY(getY() + vitesseY);
            bloquéVertical(getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);

            // Appliquer gravité

            // Appliquer déplacement vertical, ensuite vérification des collisions, si le setY l'a fait rentrer dans qqch, alors le setY de la méthode collisionVertical le fait rester en dehors du bloc

            // inertie
            boolean auSol = getCollisionBas();

            int accel;
            if (auSol) {
                accel = getAccel_sol();
            } else {
                accel = getAccel_air();
            }

            int friction;
            if (auSol) {
                friction = getFriction_sol();
            } else {
                friction = getFriction_air();
            }

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

            // Limiter la vitesse avec l'inertie
            if (vitesseX > getVitesseMax()) vitesseX = getVitesseMax();
            if (vitesseX < -getVitesseMax()) vitesseX = -getVitesseMax();

            // Appliquer le déplacement
            setX(getX() + vitesseX);
            bloquéHorizontal(getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);
        } else {
            if (!getCollisionBas()) {
                vitesseY += getGravité();
            }
            setY(getY() + vitesseY);
            bloquéVertical(getJeu().getTaille1bloc(), getJeu().getTaille1bloc()*2);
        }
    }

    // Gestion du nom
    public StringProperty getNomProperty(){
        return super.getNomProperty();
    }
    public String getNom(){
        return getNomProperty().getValue();
    }
    public void setNom(String nom){
        getNomProperty().setValue(nom);
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
            estVivant.set(false);
        }else{
            barreVie.setVie((int) (barreVie.getVie()-val));
        }
    }

    public BooleanProperty estVivantProperty() {
        return estVivant;
    }
    public boolean estVivant(){
        return estVivant.getValue();
    }


    // Gestion de l'energie
    public final IntegerProperty energieMaxProperty(){return energieMax;}
    public final int getEnergieMax() {return energieMax.get();}
    public final void setEnergieMax(int energieMax) {this.energieMax.set(energieMax);}
    public final int getEnergie() {return energie.get();}
    public final void setEnergie(int energie) {this.energie.set(energie);}
    public final void incrementEnergie(int val) {
        if(getEnergie()+val > getEnergieMax()){
            setEnergie(getEnergieMax());
        }else{
            setEnergie(getEnergie()+val);
        }
    }
    public final void decrementEnergie(int val) {
        if(getEnergie()-val < 0){
            setEnergie(0);
        }else{
            setEnergie(getEnergie()-val);
        }
    }


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

}

