package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EntiteNonVivante extends Entite{

    private BooleanProperty estActifProperty = new SimpleBooleanProperty(true);
    private int ind;

    public EntiteNonVivante(String nom, int x, int y, Map map, Jeu jeu){
        super(nom, x, y ,map, jeu);

    }

    public BooleanProperty estActifProperty() {
        return estActifProperty;
    }

    public int getInd() {
        return ind;
    }
    public void setInd(int ind) {
        this.ind = ind;
    }

    @Override
    public void bloquéVertical(int tailleL, int tailleH) {
        getJeu().getListe_projectiles().remove(ind);
        getJeu().getListe_projectilesObservable().remove(ind);
        this.estActifProperty().set(false);
        System.out.println("je suis plus dans la liste");
    }

    @Override
    public void bloquéHorizontal(int tailleL, int tailleH) {
        getJeu().getListe_projectiles().remove(ind);
        getJeu().getListe_projectilesObservable().remove(ind);
        this.estActifProperty().set(false);
        System.out.println("je suis plus dans la liste");

    }


}
