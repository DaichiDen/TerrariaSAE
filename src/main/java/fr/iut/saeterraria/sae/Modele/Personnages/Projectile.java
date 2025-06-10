package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;

public class Projectile{
    private IntegerProperty x= new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);
    private Joueur joueur;
    private StringProperty nom;
    private int gravité = 2;
    private int forceX = 0, forceY = 0;
    private BooleanProperty estActifProperty = new SimpleBooleanProperty(true);

    public Projectile(Joueur joueur, String nom) {
        this.joueur = joueur;
        this.nom = new SimpleStringProperty(nom);
        this.x.setValue(joueur.getX());
        this.y.setValue(joueur.getY());
    }

    public BooleanProperty estActifProperty() {
        return estActifProperty;
    }

    public String getNom() {
        return nom.get();
    }

    public int getX() {
        return x.getValue();
    }
    public final IntegerProperty xProperty(){
        return x;
    }
    public int getY() {
        return y.getValue();
    }
    public final IntegerProperty yProperty(){
        return y;
    }
    public void setX(int x) {
        this.x.setValue(x);
    }
    public void setY(int y) {
        this.y.setValue(y);
    }

    public int getForceX() {
        return forceX;
    }
    public int getForceY() {
        return forceY;
    }
    public void setForceX(int forceX) {
        this.forceX = forceX;
    }
    public void setForceY(int forceY) {
        this.forceY = forceY;
    }

    public int getGravité() {
        return gravité;
    }
    public void setGravité(int gravité) {
        this.gravité = gravité;
    }
}
