package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Entite {

    private IntegerProperty vie, vieMax, energieMax, x, y, def;
    private StringProperty nom;
    private double energie;
    private IntegerProperty vitesse;

    public Entite(String nom, int vie, int vieMax, int energieMax, int x, int y, int def, int vitesse) {
        this.nom = new SimpleStringProperty(nom);
        this.vie = new SimpleIntegerProperty(vie);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = energieMax;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.def = new SimpleIntegerProperty(def);
        this.vitesse = new SimpleIntegerProperty(vitesse);
    }

    public StringProperty nomProperty(){
        return this.nom;
    }
    public String getNom(){
        return this.nom.getValue();
    }
    public void setNom(String nom){
        this.nom.setValue(nom);
    }

    public final IntegerProperty vieProperty(){return vie;}
    public final int getVie() {return vie.get();}
    public final void setVie(int vie) {this.vie.set(vie);}
    public final void incrementVie(int val) {this.vie.set(vie.get()+val);}
    public final void decrementVie(int val) {this.vie.set(vie.get()-val);}

    public final IntegerProperty energieMaxProperty(){return energieMax;}
    public final int getEnergieMax() {return energieMax.get();}
    public final void setEnergieMax(int energieMax) {this.energieMax.set(energieMax);}
    public final void incrementEnergie(int val) {this.energieMax.set(energieMax.get()+val);}
    public final void decrementEnergie(int val) {this.energieMax.set(energieMax.get()-val);}

    public final IntegerProperty xProperty() {return x;}
    public final int getX() { return x.get(); }
    public final void setX(int x) { this.x.setValue(x);}

    public final IntegerProperty yProperty(){ return y; }
    public final int getY() { return y.get(); }
    public final void setY(int y) { this.y.setValue(y);}

    public final IntegerProperty defProperty(){ return def; }
    public final void setDef(int def) { this.def.setValue(def);}
    public final int getDef() { return def.get(); }

    public final IntegerProperty vitesseProperty(){return vitesse;}
    public final int getVitesse() {return vitesse.getValue();}

    public double getEnergie() {return energie;}
    public void setEnergie(double energie) {this.energie = energie;}



}