package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/*
Observateur qui sert à charger la carte verticalement en fonction du mouvement du joueur
 */
public class ObsMapY implements ChangeListener<Number> {

    private Fond decor;

    public ObsMapY(Fond fond){
        this.decor = fond;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        if(!newValue.equals(oldValue) ){
            if (Carte.getUniqueCarte().recupLigneTaille()>decor.getEnvironnement().getPrefRows()) {
                decor.updateMapY();
                decor.getEnvironnement().setPrefRows(decor.getEnvironnement().getPrefRows() + 1);
            }
        }
    }

}
