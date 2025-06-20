package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import fr.iut.saeterraria.sae.Modele.Map.Map;

public class Projectile extends Entite{
    private StringProperty nom;
    private int gravité = 1;
    private DoubleProperty forceX = new SimpleDoubleProperty(0), forceY = new SimpleDoubleProperty(0);
    private int xBloc, yBloc;
    private BooleanProperty actif;
    private String type;

    private int xExplosion, yExplosion;
    private BooleanProperty aExplosé = new SimpleBooleanProperty(false);


    public Projectile(String nom, Jeu jeu, int xJoueur, int yJoueur, int attaque, String type, int tailleL, int tailleH) {
        super(nom, xJoueur, yJoueur, jeu, attaque, tailleL, tailleH);
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

    public void explosion() {
        int x = getX() / 32;
        int y = getY() / 32;
        Map map = getJeu().getCarte();
        for (int j = x - 1; j <= x + 1; j++) {
            for (int i = y - 1; i <= y + 1; i++) {
                if (map.getCase(i, j) != 0 && map.getCase(j, i) != 10 && map.getCase(j, i) != 18) {
                    map.detruireBloc(j,i); // faire avec la resistance comme pour la pioche et la roche (voir avec luc et dedou) + mettre à jour la map héhé
                }
                Rectangle2D touché = new Rectangle2D(j*32, i*32,getJeu().getTaille1bloc(), getJeu().getTaille1bloc());
                for (int e = 0; e < getJeu().getMobs().size(); e++) {
                    if (touché.intersects(getJeu().getMobs().get(e).getHitbox()) && getJeu().getMobs().get(e).getDef()<8) {
                        getJeu().getMobs().get(e).decrementVie(8-getJeu().getMobs().get(e).getDef());
                    }
                }
                if(getJeu().getJoueur().getHitbox().intersects(touché) && getJeu().getJoueur().getDef()<5){
                    getJeu().getJoueur().decrementVie(5-getJeu().getJoueur().getDef());
                }
                xExplosion = x;
                yExplosion = y;

            }
        }
        System.out.println("bah je le met à true hein");
        setaExplosé(true);
    }
}
