package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.MediaPlayer;


public abstract class Entite {

    private Map map;
    private IntegerProperty energieMax, x, y, def;
    private StringProperty nom;
    private IntegerProperty energie;
    private IntegerProperty vitesseMax;
    private BarreVie barreVie;
    public final int taille1bloc = 32;
    private boolean enSaut = false;
    protected boolean marcheDroite = false;
    protected boolean marcheGauche = false;
    protected int vitesseY = 0;
    protected Jeu jeu;
    //constantes
    private final int gravité = 2;
    protected final int forceSaut = -18;
    //inertie et friction
    private int vitesseX = 0;
    private final int accel_sol = 5;
    private final int accel_air = 2;
    private final int friction_sol = 4;
    private final int friction_air = 1;
    private boolean collisionBas = false;
//
//    MediaPlayer damage1 = super.Sonore("/Sound/damage1.wav");
//    MediaPlayer damage2 = super.Sonore("/Sound/damage2.wav");

    public Entite(String nom, int vieMax, int energieMax, int energie, int x, int y, int def, int vitesseMax, Map map, Jeu jeu) {
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
    }


    public void setMarcheGauche(boolean marcheGauche) {
        this.marcheGauche = marcheGauche;
    }
    public void setMarcheDroite(boolean marcheDroite) {
        this.marcheDroite = marcheDroite;
    }


    public boolean getMarcheGauche() {
        return this.marcheGauche;
    }
    public boolean getMarcheDroite() {
        return this.marcheDroite;
    }

    public int getVitesseY(){
        return vitesseY;
    }

    public void sauter() {
        if (!enSaut) {
            enSaut = true;
            vitesseY = forceSaut;
        }
    }
    public boolean collisionne(int x2, int y2){
        Rectangle2D hitboxJoueur = new Rectangle2D(jeu.getJoueur().getX(), jeu.getJoueur().getY(),taille1bloc,taille1bloc*2);
        Rectangle2D hitboxMob = new Rectangle2D(x2,y2, taille1bloc, taille1bloc * 2);
        if(hitboxMob.intersects(hitboxJoueur)) {
            return true;
        }
        return false;

    }

    public void attaquer () {
        for (Entite e : jeu.getMobs()) {
            if (e == this) continue;

            int distanceX = Math.abs(e.getX() - this.getX());
            int distanceY = Math.abs(e.getY() - this.getY());

            if (distanceX < taille1bloc * 2 && distanceY < taille1bloc * 2) {
                e.decrementVie(1);
                System.out.println("Touché !");
            }
        }
    }

    public boolean peutEtreAtteint(Map map, int blocX, int blocY,double val) {
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

            if ((xi != blocX || yi != blocY) && map.getCase(yi, xi) != 3) { // Si bloc devant (obstacle)
                return false;
            }
        }

        return true;
    }

    public void mettreAJour() {
        if (estVivant()) {
            // Appliquer gravité
            if (!collisionBas) {
                vitesseY += gravité;
            }
            setY(getY() + vitesseY);
            collisionVerticale();
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
            if (marcheDroite && !marcheGauche) {
                vitesseX += accel;
            } else if (marcheGauche && !marcheDroite) {
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
        }else {
            vitesseY -= gravité;
            setY(getY() + vitesseY);
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
                    if (this.map.getCase(i, j) != 3) { // si le bloc n'est pas du ciel
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
                    if (this.map.getCase(i, j) != 3) {
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
    public StringProperty nomProperty(){
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

    public final void decrementVie(int val) {

        if(barreVie.getVie()-val < 0){
            barreVie.setVie(0);
        }else{
            barreVie.setVie(barreVie.getVie()-val);

        }

    }

    public final boolean estVivant(){
        if(barreVie.getVie() == 0){
            return false;
        }else{
            return true;
        }
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

    // Gestion du positionnement horizontal
    public final IntegerProperty xProperty() {return x;}
    public int getX() { return x.get(); }
    public final void setX(int x) { this.x.setValue(x);}
    // Gestion su positionnement vertical
    public final IntegerProperty yProperty(){ return y; }
    public int getY() { return y.get(); }
    public final void setY(int y) { this.y.setValue(y);}

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
