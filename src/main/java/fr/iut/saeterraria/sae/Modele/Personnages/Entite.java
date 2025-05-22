package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.MediaPlayer;


public abstract class Entite {

    private IntegerProperty energieMax, x, y, def;
    private StringProperty nom;
    private IntegerProperty energie;
    private IntegerProperty vitesseMax;
    private BarreVie barreVie;
    public final int taille1bloc = 32;
//
//    MediaPlayer damage1 = super.Sonore("/Sound/damage1.wav");
//    MediaPlayer damage2 = super.Sonore("/Sound/damage2.wav");

    public Entite(String nom,int vieMax, int energieMax, int energie, int x, int y, int def, int vitesseMax) {
        this.nom = new SimpleStringProperty(nom);
        this.barreVie = new BarreVie(vieMax);
        this.energieMax = new SimpleIntegerProperty(energieMax);
        this.energie = new SimpleIntegerProperty(energie);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.def = new SimpleIntegerProperty(def);
        this.vitesseMax = new SimpleIntegerProperty(vitesseMax);
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
    public BarreVie getBarreVie(){
        return this.barreVie;
    }
    public final void incrementVie(int val) {
        if(barreVie.getVie()+val > barreVie.getVieMax()){
            barreVie.setVie(barreVie.getVie());
        }else{
            barreVie.setVie(barreVie.getVie()+val);
        }
    }

    public final void decrementVie(int val) {

            int n = (int) (Math.random()*10);
            switch (n){
                case 0:
//                    damage1.play();
//                    damage1.stop();
                    break;
                case 1:
//                    damage2.play();
//                    damage2.stop();
                   break;
                case 2:
                    break;
                default:
                    System.out.println("dmg"+n);
                    break;
            }

        if(barreVie.getVie()-val < 0){
            barreVie.setVie(0);
        }else{
            barreVie.setVie(barreVie.getVie()-val);

        }

    }

    public final boolean estVivant(){
        if(barreVie.getVie() == 0){
            return false;
        }else{
            return true;
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
    public final int getX() { return x.get(); }
    public final void setX(int x) { this.x.setValue(x);}
    // Gestion su positionnement vertical
    public final IntegerProperty yProperty(){ return y; }
    public final int getY() { return y.get(); }
    public final void setY(int y) { this.y.setValue(y);}

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
    public final IntegerProperty vitesseMaxProperty(){return vitesseMax;}
    public final int getVitesseMax() {return vitesseMax.getValue();}
    public void setVitesseMax(int vitesse) {this.vitesseMax.setValue(vitesse);}

}