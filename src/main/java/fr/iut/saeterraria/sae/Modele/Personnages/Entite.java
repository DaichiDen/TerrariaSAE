package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Entite {

    private IntegerProperty vie, vieMax, energieMax, x, y, def;
    private StringProperty nom;
    private IntegerProperty energie;
    private IntegerProperty vitesse;

    public Entite(String nom, int vie, int vieMax, int energieMax, int energie, int x, int y, int def, int vitesse) {
        this.nom = new SimpleStringProperty(nom);
        this.vieMax = new SimpleIntegerProperty(vieMax);
        this.vie = new SimpleIntegerProperty(vie);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
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
    public final void decrementVie(int val) {
        if(getVie()-val < 0){
            setVie(0);
        }else{
            setVie(getVie()-val);
        }
    }

    public final IntegerProperty energieMaxProperty(){return energieMax;}
    public final int getEnergieMax() {return energieMax.get();}
    public final void setEnergieMax(int energieMax) {this.energieMax.set(energieMax);}
    public final int getEnergie() {return energie.get();}
    public final void setEnergie(int energie) {this.energie.set(energie);}
    public final void incrementEnergie(int val) {
        if(getEnergie()+val > getEnergieMax()){
            setEnergie(getEnergie()+val);
        }else{
            setEnergie(energie.get()+val);
        }
    }
    public final void decrementEnergie(int val) {
        if(getEnergie()-val < 0){
            setEnergie(0);
        }else{
            setEnergie(getEnergie()-val);
        }
    }

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

}