package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;

/*
 * Classe abstraite représentant une entité du jeu (joueur, ennemi, projectile...).
 * Elle définit les propriétés communes à toutes les entités :
 * position (x, y), taille, hitbox, direction de déplacement, attaque (dégâts infligés), gravité appliqué sur l'entité.
 *
 * Elle donne les méthodes utilisées par toutes les entités pour la détection des collisions.
 */

public abstract class Entite {

    private IntegerProperty x, y;
    private IntegerProperty xHitbox, yHitbox;

    private static int compteurId = -1;
    private final IntegerProperty id = new SimpleIntegerProperty();

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
    private boolean collisionBas = false;

    public Entite(int x, int y, int attaque, int tailleL, int tailleH) {
        setId(compteurId++);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.attaque = attaque;
        this.tailleL = tailleL;
        this.tailleH = tailleH;

        this.xHitbox = new SimpleIntegerProperty(x);
        this.yHitbox = new SimpleIntegerProperty(y);
        this.xHitbox.bind(this.x);
        this.yHitbox.bind(this.y);
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D(getxHitbox(), getyHitbox(), tailleL, tailleH);
    }

    public int getxHitbox() {
        return this.xHitbox.getValue();
    }

    public int getyHitbox(){
        return this.yHitbox.getValue();
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

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getxBloc() {
        return xBloc;
    }

    public int getyBloc() {
        return yBloc;
    }

    public int getGravité() {
        return gravité;
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

    public boolean estDansMap(int x,int y){
        boolean estDansLesLimites = false;
        if((x >= 0 && x < Carte.getUniqueCarte().recupLigneTaille()) && (y >= 0 && y < Carte.getUniqueCarte().recupColonneTaille())){
            estDansLesLimites=true;
        }
        return estDansLesLimites;
    }


    public boolean collisionVerticale() { /** Fonction qui teste la collision verticale de façon dynamique, regarde seulement les 3 blocs autour du joueur (verticalement et horizontalement)*/
        collisionBas = false;
        Rectangle2D hitboxEntite = getHitbox();
        int caseX =(getX() / Jeu.getUniqueJeu().getTaille1bloc());
        int caseY =(getY() / Jeu.getUniqueJeu().getTaille1bloc());
        return testerCollisionVerticale(hitboxEntite, caseX, caseY);
    }

    public boolean testerCollisionVerticale(Rectangle2D hitboxEntite, int caseX,int caseY){
        //boucle sur les 4 blocs autour du joueur , i+1 i-1 ,j+1 j-1
        for (int i = caseY - 1; i <= caseY + 2; i++) { // +2 pour la taille du personnage (2 blocs de hauteur)
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (estDansMap(i,j)) {
                    if (!Carte.getUniqueCarte().blocTraversable(i,j)) {
                        xBloc = j*Jeu.getUniqueJeu().getTaille1bloc();
                        yBloc = i*Jeu.getUniqueJeu().getTaille1bloc();
                        Rectangle2D hitboxBloc = new Rectangle2D(xBloc, yBloc, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc());
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
        int caseX =  (this.getX() / Jeu.getUniqueJeu().getTaille1bloc());
        int caseY = (this.getY() / Jeu.getUniqueJeu().getTaille1bloc());
        return testerCollisionHorizontale(hitboxEntite, caseX, caseY);
    }

    public boolean testerCollisionHorizontale(Rectangle2D hitboxEntite, int caseX,int caseY){
        for (int i = caseY ; i <= caseY + 2; i++) {
            for (int j = caseX - 1; j <= caseX + 1; j++) {
                if (estDansMap(i,j)) {
                    if (!Carte.getUniqueCarte().blocTraversable(i,j)) {
                        xBloc = j*Jeu.getUniqueJeu().getTaille1bloc();
                        yBloc = i*Jeu.getUniqueJeu().getTaille1bloc();
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



