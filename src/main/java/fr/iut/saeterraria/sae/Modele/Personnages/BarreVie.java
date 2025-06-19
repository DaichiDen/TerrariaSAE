package fr.iut.saeterraria.sae.Modele.Personnages;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BarreVie {
    private DoubleProperty vieMax;
    private DoubleProperty vie;

    public BarreVie(int  vieMax) {
        this.vie = new SimpleDoubleProperty(vieMax);
        this.vieMax = new SimpleDoubleProperty(vieMax);
    }

    public final DoubleProperty vieMaxProperty() { return vieMax; }
    public final double getVieMax() {return vieMax.getValue();}
    public final void setVieMax(double vie) {this.vieMax.setValue(vie);}
    public final DoubleProperty vieProperty(){return vie;}
    public final Double getVie() {return vie.getValue();}
    public final void setVie(double vie) {this.vie.setValue(vie);}


}


