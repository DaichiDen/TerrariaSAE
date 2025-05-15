package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;

public abstract class Entite {

    private IntegerProperty vie, vieMax, energieMax, x, y, def;
    private StringProperty nom;
    private IntegerProperty energie;
    private IntegerProperty vitesse;
    private Rectangle2D hitbox;

    public Entite(String nom, int vie, int vieMax, int energieMax, int energie, double x, double y, int def, int vitesse, Rectangle2D hitbox) {
        this.nom = new SimpleStringProperty(nom);
        this.vieMax = new SimpleIntegerProperty(vieMax);
        this.vie = new SimpleIntegerProperty(vie);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
        this.x = new SimpleIntegerProperty((int) x);
        this.y = new SimpleIntegerProperty((int) y);
        this.def = new SimpleIntegerProperty(def);
        this.vitesse = new SimpleIntegerProperty(vitesse);
        this.hitbox = hitbox;
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
    public final IntegerProperty vieProperty(){return vie;}
    public final int getVieMax() {return vieMax.get();}
    public final void setVieMax(int vie) {this.vieMax.set(vie);}
    public final int getVie() {return vie.get();}
    public final void setVie(int vie) {this.vie.set(vie);}
    public final void incrementVie(int val) {
        if(getVie()+val > getVieMax()){
            setVie(getVieMax());
        }else{
            setVie(getVie()+val);
        }
    }
    public Rectangle2D getHitbox(){
        return this.hitbox;
    }
    public final void decrementVie(int val) {
        if(getVie()-val < 0){
            setVie(0);
        }else{
            setVie(getVie()-val);
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
    public final double getX() { return x.get(); }
    public final void setX(double x) { this.x.setValue(x);}
    // Gestion su positionnement vertical
    public final IntegerProperty yProperty(){ return y; }
    public final double getY() { return y.get(); }
    public final void setY(double y) { this.y.setValue(y);}

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
    public final IntegerProperty vitesseProperty(){return vitesse;}
    public final int getVitesse() {return vitesse.getValue();}
    public void setVitesse(int vitesse) {this.vitesse.setValue(vitesse);}

}