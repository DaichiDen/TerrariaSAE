package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ListObsMap implements ChangeListener<Number> {

    private Jeu jeu;

    public ListObsMap(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

    }

}
