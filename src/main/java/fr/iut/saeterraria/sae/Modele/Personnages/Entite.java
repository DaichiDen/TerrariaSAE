package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;


public abstract class Entite {

    private IntegerProperty x, y;
    private StringProperty nom;
    private Jeu jeu;
    private int attaque;

    private BooleanProperty marcheDroite = new SimpleBooleanProperty(false);
    private BooleanProperty marcheGauche = new SimpleBooleanProperty(false);

    private int tailleH, tailleL;

    private final int gravité = 2;
    private final static int accel_sol = 5;
    private final static int accel_air = 2;
    private final static int friction_sol = 4;
    private final static int friction_air = 1;

    private int xBloc, yBloc;
    private int la_case;
    private boolean collisionBas = false;

//    MediaPlayer damage1 = super.Sonore("/Sound/damage1.wav");
//    MediaPlayer damage2 = super.Sonore("/Sound/damage2.wav");

    public Entite(String nom, int x, int y, Jeu jeu, int attaque) {
        this.nom = new SimpleStringProperty(nom);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.jeu = jeu;
        this.attaque = attaque;
    }

    public int getTailleH() {
        return tailleH;
    }
    public int getTailleL() {
        return tailleL;
    }
    public void setTailleH(int tailleH) {
        this.tailleH = tailleH;
    }
    public void setTailleL(int tailleL) {
        this.tailleL = tailleL;
    }

    public static int getAccel_air() {
        return accel_air;
    }

    public static int getAccel_sol() {
        return accel_sol;
    }

    public static int getFriction_air() {
        return friction_air;
    }

    public static int getFriction_sol() {
        return friction_sol;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty getNomProperty() {
        return nom;
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

    public int getGravité() {
        return gravité;
    }

    public int getLa_case() {
        return la_case;
    }

    public void setLa_case(int la_case) {
        this.la_case = la_case;
    }

    public boolean getCollisionBas() {
        return collisionBas;
    }

    public void setCollisionBas(boolean collisionBas) {
        this.collisionBas = collisionBas;
    }

    public BooleanProperty marcheGaucheProperty() {
        return marcheGauche;
    }

    public boolean getMarcheDroite() {
        return marcheDroite.get();
    }

    public void setMarcheDroite(boolean val) {
        marcheDroite.set(val);
    }

    public BooleanProperty marcheDroiteProperty() {
        return marcheDroite;
    }

    public boolean getMarcheGauche() {
        return marcheGauche.get();
    }

    public void setMarcheGauche(boolean val) {
        marcheGauche.set(val);
    }

    // Gestion du positionnement horizontal
    public final IntegerProperty xProperty() {
        return x;
    }

    public int getX() {
        return x.getValue();
    }

    public final void setX(int x) {
        this.x.setValue(x);
    }

    // Gestion su positionnement vertical
    public final IntegerProperty yProperty() {
        return y;
    }

    public int getY() {
        return y.getValue();
    }

    public final void setY(int y) {
        this.y.setValue(y);
    }
    public int getAttaque() { return this.attaque;}
    public void setAttaque(int attaque) { this.attaque = attaque;}
    public Jeu getJeu() {
        return jeu;
    }


    // Niké
//    public void collision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ectile(Projectile projectile, int ind) {
//        Rectangle2D hitboxProjectile = new Rectangle2D(projectile.getX(), projectile.getY(), jeu.getTaille1bloc( , jeu.getTaille1bloc();
//
//        System.out.println("hitbox fleche : "+projectile.getX()+","+(projectile.getX()+jeu.getTaille1bloc()+" / "+projectile.getY()+","+(projectile.getY()+jeu.getTaille1bloc());
//
//        int i = (int) (projectile.getX() / jeu.getTaille1bloc();
//        int j = (int) (projectile.getY() / jeu.getTaille1bloc();
//
//        int x = (int) (projectile.getX());
//        int y = (int) (projectile.getY());
//
//
//        if (jeu.getCarte().getCase(j, i) != 0) { // si le bloc n'est pas du ciel
//
//            Rectangle2D hitboxBloc = new Rectangle2D(x,y, jeu.getTaille1bloc(, jeu.getTaille1bloc();//Bloc à la position de la flèche
//
//            System.out.println("hitbox bloc : "+x+","+(x+jeu.getTaille1bloc()+" / "+y+","+(y+jeu.getTaille1bloc());
//
//            System.out.println("coordonnées de la flèche : "+projectile.getX()+", "+projectile.getY());
//
//            System.out.println("coordonnées du bloc  : "+j+", "+i);
//
//            System.out.println("la fleche touche ! : "+hitboxProjectile.intersects(hitboxBloc));
//
//            if(hitboxProjectile.intersects(hitboxBloc)){
//                projectiles.remove(ind);
//                liste_projectiles.remove(ind);
//                projectile.estActifProperty().set(false);
//                System.out.println("je suis plus dans la liste");
//            }
//        }
//        else {
//            System.out.println("je suis dans le ciel là ohéééé");
//        }
//    }


    public boolean collisionVerticale(int tailleL, int tailleH) { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;

        setTailleH(tailleH);
        setTailleL(tailleL);

        Rectangle2D hitboxEntite = new Rectangle2D(getX(), getY(), tailleL, tailleH);

        int caseX = (int) (getX() / jeu.getTaille1bloc());
        int caseY = (int) (getY() / jeu.getTaille1bloc());

        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {

                if (i >= 0 && i < this.jeu.getCarte().getLigne() && j >= 0 && j < jeu.getCarte().getColonne()) {
                    if (jeu.getCarte().getCase(i, j) != 0) { // si le bloc n'est pas du ciel

                        xBloc = jeu.getCarte().getCoordonnéesX(j);
                        yBloc = jeu.getCarte().getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, jeu.getTaille1bloc(), jeu.getTaille1bloc()); // création d'un rectangle de hitbox pour le bloc en cours
                        if (hitboxEntite.intersects(hitboxBloc)) {// si le rectangle du joueur se superpose au carré du bloc alors :
                            collisionBas = true;
                            return true;

                        }
                    }
                }
            }
            }

        return false;
    }


    //    public void collisionVerticale(int tailleL,int tailleH) { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
//        collisionBas = false;
//
//        Rectangle2D hitboxEntite = new Rectangle2D(getX(),getY(),tailleL,tailleH);
//
//        int caseX = (int) (getX() / jeu.getTaille1bloc();
//        int caseY = (int) (getY() / jeu.getTaille1bloc();
//        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
//        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
//            for (int j = caseX - 1; j <= caseX + 1; j++) {
//                if (i >= 0 && i < jeu.getCarte().getLigne() && j >= 0 && j < jeu.getCarte().getColonne()) {
//                    la_case = jeu.getCarte().getCase(i, j);
//                    if (jeu.getCarte().getCase(i, j) != 0) { // si le bloc n'est pas du ciel
//                        xBloc = jeu.getCarte().getCoordonnéesX(j);
//                        yBloc = jeu.getCarte().getCoordonnéesY(i);
//                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, jeu.getTaille1bloc(, jeu.getTaille1bloc(); // création d'un rectangle de hitbox pour le bloc en cours
//
//                        if (hitboxEntite.intersects(hitboxBloc)) { // si le rectangle du joueur se superpose au carré du bloc alors :
//                            int blocHaut = yBloc;
//                            int blocBas = yBloc + jeu.getTaille1bloc(;
//                            int joueurHaut = getY();
//                            int joueurBas = getY() + (jeu.getTaille1bloc(*2);
//
//                            if (joueurBas >= blocHaut && vitesseY >= 0 && joueurHaut < blocHaut) { //vitesseY >=0 vérifie si le joueur est entrain de tomber
//                                collisionBas = true;
//                                enSaut = false;
//                                vitesseY = 0;
//                                setY(blocHaut - (jeu.getTaille1bloc(*2));
//                            }
//                            else if (joueurHaut <= blocBas && vitesseY < 0 && joueurBas > blocBas) {
//                                vitesseY = 0;
//                                setY(blocBas);
//                            }
//                            if(jeu.getCarte().getCase(i,j) == 4){
//                                this.decrementVie(1);
//
//
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    }
    public boolean collisionHorizontale(int tailleL, int tailleH) {

        Rectangle2D hitboxJoueur = new Rectangle2D(this.getX(), this.getY(), tailleL, tailleH);

        setTailleH(tailleH);
        setTailleL(tailleL);

        int caseX = (int) (this.getX() / jeu.getTaille1bloc());
        int caseY = (int) (this.getY() / jeu.getTaille1bloc());


        for (int i = caseY ; i <= caseY + 2; i++) {
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < jeu.getCarte().getLigne() && j >= 0 && j < jeu.getCarte().getColonne()) {
                    if (jeu.getCarte().getCase(i, j) != 0) {

                        xBloc = jeu.getCarte().getCoordonnéesX(j);
                        yBloc = jeu.getCarte().getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, tailleL, tailleH);

                        if (hitboxJoueur.intersects(hitboxBloc)) {

                            return true;

                        }
                    }
                }
            }
        }
        return false;
    }




}



