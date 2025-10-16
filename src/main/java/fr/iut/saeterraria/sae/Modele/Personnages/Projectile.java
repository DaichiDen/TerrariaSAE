package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;

public class Projectile extends Entite{
    private StringProperty nom;
    private int gravité = 1;
    private DoubleProperty forceX = new SimpleDoubleProperty(0), forceY = new SimpleDoubleProperty(0);
    private int xBloc, yBloc;
    private BooleanProperty actif;
    private String type;

    private int xExplosion, yExplosion;
    private BooleanProperty aExplosé = new SimpleBooleanProperty(false);


    public Projectile(String nom, int xJoueur, int yJoueur, int attaque, String type, int tailleL, int tailleH) {
        super(nom, xJoueur, yJoueur, attaque, tailleL, tailleH);
        this.nom = new SimpleStringProperty(nom);
        this.actif = new SimpleBooleanProperty(true);
        this.type=type;

    }

    public BooleanProperty aExploséProperty() {
        return aExplosé;
    }
    public boolean getaExplosé(){
        return aExplosé.getValue();
    }
    public void setaExplosé(boolean a){
        aExplosé.setValue(a);
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int getGravité() {
        return gravité;
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

    //TODO faire des sous-classes : l'explosion c'est uniquement pour les boules de feu, pas pur les balles
    public void explosion() {
        int x = getX() / 32;
        int y = getY() / 32;
        for (int j = x - 1; j <= x + 1; j++) {
            for (int i = y - 1; i <= y + 1; i++) {
                if (!Carte.getUniqueCarte().blocTraversable(i,j)) {
                    Carte.getUniqueCarte().detruireBloc(j,i); // faire avec la resistance comme pour la pioche et la roche (voir avec luc et dedou) + mettre à jour la map héhé
                }
                Rectangle2D touché = new Rectangle2D(j*32, i*32,Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc());
                for (int e = 0; e < Jeu.getUniqueJeu().getMobs().size(); e++) {
                    if (touché.intersects(Jeu.getUniqueJeu().getMobs().get(e).getHitbox()) && Jeu.getUniqueJeu().getMobs().get(e).getDef()<8) {
                        Jeu.getUniqueJeu().getMobs().get(e).decrementVie(8-Jeu.getUniqueJeu().getMobs().get(e).getDef());
                    }
                }
                if(Joueur.getUniqueJoueur().getHitbox().intersects(touché) && Joueur.getUniqueJoueur().getDef()<5){
                    Joueur.getUniqueJoueur().decrementVie(5-Joueur.getUniqueJoueur().getDef());
                }
                xExplosion = x;
                yExplosion = y;
            }
        }
        setaExplosé(true);
    }
}
