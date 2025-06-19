package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;


public abstract class Entite {

    private IntegerProperty x, y;
    private IntegerProperty xHitbox, yHitbox;
    private StringProperty nom;
    private Jeu jeu;
    private int attaque;
    private Rectangle2D hitbox;

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

    public Entite(String nom, int x, int y, Jeu jeu, int attaque, int tailleL, int tailleH) {
        this.nom = new SimpleStringProperty(nom);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.jeu = jeu;
        this.attaque = attaque;
        this.tailleL = tailleL;
        this.tailleH = tailleH;

        this.xHitbox = new SimpleIntegerProperty(x);
        this.yHitbox = new SimpleIntegerProperty(y);
        this.xHitbox.bind(this.x);
        this.yHitbox.bind(this.y);
        this.hitbox = new Rectangle2D(getxHitbox(), getyHitbox(), tailleL, tailleH);
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D(getxHitbox(), getyHitbox(), tailleL, tailleH);
    }

    public IntegerProperty xHitboxProperty(){
        return this.xHitbox;
    }
    public int getxHitbox() {
        return this.xHitbox.getValue();
    }
    public void setxHitbox(int xHitbox) {
        this.xHitbox.set(xHitbox);
    }

    public IntegerProperty yHitboxProperty(){
        return this.yHitbox;
    }
    public int getyHitbox(){
        return this.yHitbox.getValue();
    }
    public void setyHitbox(int yHitbox) {
        this.yHitbox.set(yHitbox);
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


    public boolean collisionVerticale() { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;

        setTailleH(tailleH);
        setTailleL(tailleL);

        Rectangle2D hitboxEntite = getHitbox();

        int caseX = (int) (getX() / jeu.getTaille1bloc());
        int caseY = (int) (getY() / jeu.getTaille1bloc());

        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {

                if (i >= 0 && i < this.jeu.getCarte().getLigne() && j >= 0 && j < jeu.getCarte().getColonne()) {
                    if (jeu.getCarte().getCase(i, j) != 0 || jeu.getCarte().getCase(i, j) != 18 || jeu.getCarte().getCase(i, j) != 22 ) { // si le bloc n'est pas du ciel

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

    public boolean collisionHorizontale() {

        Rectangle2D hitboxEntite = getHitbox();

        setTailleH(tailleH);
        setTailleL(tailleL);

        int caseX = (int) (this.getX() / jeu.getTaille1bloc());
        int caseY = (int) (this.getY() / jeu.getTaille1bloc());


        for (int i = caseY ; i <= caseY + 2; i++) {
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (i >= 0 && i < jeu.getCarte().getLigne() && j >= 0 && j < jeu.getCarte().getColonne()) {
                    if (jeu.getCarte().getCase(i, j) != 0 || jeu.getCarte().getCase(i, j) != 18 || jeu.getCarte().getCase(i, j) != 22 ) {

                        xBloc = jeu.getCarte().getCoordonnéesX(j);
                        yBloc = jeu.getCarte().getCoordonnéesY(i);
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, tailleL, tailleH);

                        if (hitboxEntite.intersects(hitboxBloc)) {

                            return true;

                        }
                    }
                }
            }
        }
        return false;
    }





}



