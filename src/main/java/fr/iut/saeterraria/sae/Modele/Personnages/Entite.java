package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;


public abstract class Entite {

    private Map map;
    private IntegerProperty energieMax, x, y, def;
    private StringProperty nom;
    private IntegerProperty energie;
    private IntegerProperty vitesseMax;
    private BarreVie barreVie;

    private BooleanProperty estVivant;

    public final static int taille1bloc = 32;

    private boolean enSaut = false;
    private BooleanProperty marcheDroite = new SimpleBooleanProperty(false);
    private BooleanProperty marcheGauche = new SimpleBooleanProperty(false);
    private int vitesseY = 0;
    private Jeu jeu;
    //constantes
    private final int gravité = 2;
    protected final int forceSaut = -18;
    //inertie et friction
    private int vitesseX = 0;
    private final static int accel_sol = 5;
    private final static int accel_air = 2;
    private final static int friction_sol = 4;
    private final static int friction_air = 1;
    private boolean collisionBas = false;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;
//
//    MediaPlayer damage1 = super.Sonore("/Sound/damage1.wav");
//    MediaPlayer damage2 = super.Sonore("/Sound/damage2.wav");

    public Entite(String nom, int vieMax, int energieMax, int energie, int x, int y, int def, int vitesseMax, Map map, Jeu jeu) {
        projectiles= new ArrayList<>();
        this.nom = new SimpleStringProperty(nom);
        this.barreVie = new BarreVie(vieMax);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.def = new SimpleIntegerProperty(def);
        this.vitesseMax = new SimpleIntegerProperty(vitesseMax);
        this.map = map;
        this.jeu=jeu;
        this.estVivant= new SimpleBooleanProperty(true);
        liste_projectiles = FXCollections.observableArrayList(projectiles);

    }

    // Gestion du positionnement horizontal
    public final IntegerProperty xProperty() {return x;}
    public int getX() { return x.getValue(); }
    public final void setX(int x) {this.x.setValue(x);}
    // Gestion su positionnement vertical
    public final IntegerProperty yProperty(){ return y; }
    public int getY() { return y.getValue(); }
    public final void setY(int y) { this.y.setValue(y);}

    public Jeu getJeu() { return jeu; }

    public boolean getMarcheGauche() { return marcheGauche.get(); }
    public void setMarcheGauche(boolean val) { marcheGauche.set(val); }
    public BooleanProperty marcheGaucheProperty() { return marcheGauche; }

    public boolean getMarcheDroite() { return marcheDroite.get(); }
    public void setMarcheDroite(boolean val) { marcheDroite.set(val); }
    public BooleanProperty marcheDroiteProperty() { return marcheDroite; }

    public int getVitesseY(){
        return vitesseY;
    }

    public ArrayList<Projectile> getListe_projectiles() {
        return projectiles;
    }
    public ObservableList<Projectile> getListe_projectilesObservable() {
        return liste_projectiles;
    }

    public void sauter() {
        if (!enSaut && this.estVivant() ) {
            enSaut = true;
            vitesseY = forceSaut;
        }
    }


    public abstract void attaquer(int x, int y, int range);

    public void tirerProjectile(Projectile projectile, int cibleX, int cibleY) {
        // Position de l'entité
        int ex = this.getX();
        int ey = this.getY();
        System.out.println("ex "+ex+", ey "+ey);

        System.out.println(cibleX+" cibles "+cibleY);

        // Direction du tirq
        float dx = cibleX - ex;
        float dy = cibleY - ey;
        System.out.println("dx "+dx+", dy "+dy);

        // Normalisation du vecteur (dx, dy)
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) distance = 1; // éviter division par zéro
        System.out.println(distance +" distance");

        // Vitesse initiale (puissance du tir)
        float puissance = 20.0f;

        float vx = (dx / distance) * puissance;
        float vy = (dy / distance) * puissance;

        System.out.println(vx +"vx, vy "+vy);

        // Appliquer la vitesse initiale au projectile
        projectile.setForceX(vx);
        projectile.setForceY(vy);

        // Position de départ = entité
        projectile.setX(ex);
        projectile.setY(ey);

        // Ajouter aux listes
        projectiles.add(projectile);
        liste_projectiles.add(projectile);
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

            if ((xi != blocX || yi != blocY) && map.getCase(yi, xi) != 0) { // Si bloc devant (obstacle)
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
            if (!collisionBas) {
                vitesseY += gravité;
            }
            setY(getY() + vitesseY);
            collisionVerticale();
            // Appliquer gravité

            // Appliquer déplacement vertical, ensuite vérification des collisions, si le setY l'a fait rentrer dans qqch, alors le setY de la méthode collisionVertical le fait rester en dehors du bloc

            // inertie
            boolean auSol = collisionBas;

            int accel;
            if (auSol) {
                accel = accel_sol;
            } else {
                accel = accel_air;
            }

            int friction;
            if (auSol) {
                friction = friction_sol;
            } else {
                friction = friction_air;
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
            collisionHorizontale();
        } else {
            if (!collisionBas) {
                vitesseY += gravité;
            }
            setY(getY() + vitesseY);
            collisionVerticale();
        }
    }
    public void màjProjectiles() {
        if (liste_projectiles != null) {
            for (int i = 0; i < liste_projectiles.size(); i++) {
                liste_projectiles.get(i).setX(liste_projectiles.get(i).getX() + liste_projectiles.get(i).getForceX());

                liste_projectiles.get(i).setForceY((float) (liste_projectiles.get(i).getForceY() + liste_projectiles.get(i).getGravité()));

                liste_projectiles.get(i).setY(liste_projectiles.get(i).getY() + liste_projectiles.get(i).getForceY());

                collisionProjectile(liste_projectiles.get(i), i);
            }
        }
    }

    public void collisionProjectile(Projectile projectile, int ind) {
        Rectangle2D hitboxProjectile = new Rectangle2D(this.getX(), this.getY(), taille1bloc / 4, taille1bloc / 4);

        int j = (int) (projectile.getX() / taille1bloc);
        int i = (int) (projectile.getY() / taille1bloc);

        if (i >= 0 && i < this.map.getLigne() && j >= 0 && j < this.map.getColonne()) {
            if (this.map.getCase(i, j) != 0) { // si le bloc n'est pas du ciel
                int xBloc = this.map.getCoordonnéesX(j);
                int yBloc = this.map.getCoordonnéesY(i);
                Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, taille1bloc, taille1bloc * 2);
                if(hitboxBloc.intersects(hitboxProjectile)){
                    liste_projectiles.remove(ind);
                    System.out.println("je suis plus dans la liste");
                }
            }
        }
    }



    public void collisionVerticale() { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;

        Rectangle2D hitboxJoueur = new Rectangle2D(getX(),getY(),taille1bloc-2,taille1bloc*2);

        int caseX = (int) (getX() / taille1bloc);
        int caseY = (int) (getY() / taille1bloc);
        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < this.map.getLigne() && j >= 0 && j < this.map.getColonne()) {
                    if (this.map.getCase(i, j) != 0) { // si le bloc n'est pas du ciel
                        int xBloc = this.map.getCoordonnéesX(j);
                        int yBloc = this.map.getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, taille1bloc, taille1bloc*2); // création d'un rectangle de hitbox pour le bloc en cours

                        if (hitboxJoueur.intersects(hitboxBloc)) { // si le rectangle du joueur se superpose au carré du bloc alors :
                            int blocHaut = yBloc;
                            int blocBas = yBloc + taille1bloc;
                            int joueurHaut = getY();
                            int joueurBas = getY() + (taille1bloc*2);

                            if (joueurBas >= blocHaut && vitesseY >= 0 && joueurHaut < blocHaut) { //vitesseY >=0 vérifie si le joueur est entrain de tomber
                                collisionBas = true;
                                enSaut = false;
                                vitesseY = 0;
                                setY(blocHaut - (taille1bloc*2));
                            }
                            else if (joueurHaut <= blocBas && vitesseY < 0 && joueurBas > blocBas) {
                                vitesseY = 0;
                                setY(blocBas);
                            }
                            if(this.map.getCase(i,j) == 4){
                                this.decrementVie(1);


                            }
                        }
                    }

                }
            }
        }
    }
    public void collisionHorizontale() {

        Rectangle2D hitboxJoueur = new Rectangle2D(this.getX(), this.getY(), taille1bloc-2, taille1bloc*2);

        int caseX = (int) (this.getX() / taille1bloc);
        int caseY = (int) (this.getY() / taille1bloc);

        boolean collisionDroite = false;
        boolean collisionGauche = false;

        for (int i = caseY - 1; i <= caseY + 2; i++) {
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < this.map.getLigne() && j >= 0 && j < this.map.getColonne()) {
                    if (this.map.getCase(i, j) != 0) {
                        int xBloc = this.map.getCoordonnéesX(j);
                        int yBloc = this.map.getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, taille1bloc, taille1bloc);

                        if (hitboxJoueur.intersects(hitboxBloc)) {
                            // Bords du bloc
                            int blocGauche = xBloc;
                            int blocDroite = xBloc + taille1bloc;
                            // Bords du joueur
                            int joueurGauche = this.getX();
                            int joueurDroite = this.getX() + taille1bloc;

                            if (joueurDroite > blocGauche && joueurGauche < blocGauche) {
                                // Collision côté droit du joueur contre gauche du bloc
                                collisionDroite = true;
                                // Repositionner le joueur pile à gauche du bloc
                                this.setX(blocGauche - taille1bloc);
                            } else if (joueurGauche < blocDroite && joueurDroite > blocDroite) {
                                // Collision côté gauche du joueur contre droite du bloc
                                collisionGauche = true;
                                // Repositionner le joueur pile à droite du bloc
                                this.setX(blocDroite);
                            }
                        }
                    }
                }
            }
        }

        if (collisionDroite) setMarcheDroite(false);
        if (collisionGauche) setMarcheGauche(false);
    }


    // Gestion du nom
    public StringProperty getNomProperty(){
        return this.nom;
    }
    public String getNom(){
        return this.nom.getValue();
    }
    public void setNom(String nom){
        this.nom.setValue(nom);
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
