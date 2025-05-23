package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BarreVie {
    private int x = 800;
    private int y = 10;
    private IntegerProperty vieMax;
    private IntegerProperty vie;

    public BarreVie(int  vieMax) {
        this.vie = new SimpleIntegerProperty(vieMax);
        this.vieMax = new SimpleIntegerProperty(vieMax);

    }

    public final IntegerProperty vieProperty(){return vie;}
    public final int getVieMax() {return vieMax.get();}
    public final void setVieMax(int vie) {this.vieMax.set(vie);}
    public final int getVie() {return vie.get();}
    public final void setVie(int vie) {this.vie.set(vie);}

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}


