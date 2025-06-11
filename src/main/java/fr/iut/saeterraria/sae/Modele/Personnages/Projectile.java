package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import javafx.beans.property.*;

public class Projectile{
    private FloatProperty x= new SimpleFloatProperty(0);
    private FloatProperty y = new SimpleFloatProperty(0);
    private Joueur joueur;
    private StringProperty nom;
    private double gravité = 0.5;
    private float forceX = 0, forceY = 0;
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

    public float getX() {
        return x.getValue();
    }
    public final FloatProperty xProperty(){
        return x;
    }
    public float getY() {
        return y.getValue();
    }
    public final FloatProperty yProperty(){
        return y;
    }
    public void setX(float x) {
        this.x.setValue(x);
    }
    public void setY(float y) {
        this.y.setValue(y);
    }

    public float getForceX() {
        return forceX;
    }
    public float getForceY() {
        return forceY;
    }
    public void setForceX(float forceX) {
        this.forceX = forceX;
    }
    public void setForceY(float forceY) {
        this.forceY = forceY;
    }

    public double getGravité() {
        return gravité;
    }
    public void setGravité(int gravité) {
        this.gravité = gravité;
    }
}
