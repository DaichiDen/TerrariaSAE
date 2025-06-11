package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsMapY implements ChangeListener<Number> {

    private Jeu jeu;
    private Fond fond;

    public ObsMapY(Jeu jeu, Fond fond){
        this.jeu = jeu;
        this.fond = fond;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        System.out.println(newValue);
        if(!newValue.equals(oldValue) ){
            fond.updateMapY();
            fond.getEnvironnement().setPrefRows(fond.getEnvironnement().getPrefRows()+1);
        }
    }

}
