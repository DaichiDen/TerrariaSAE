package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Vue.Fond;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/*
Observateur qui sert à charger la carte horizontalement en fonction du mouvement du joueur
 */
public class ObsMapX implements ChangeListener<Number> {

    private Fond decor;

    public ObsMapX(Fond fond){
        this.decor = fond;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        if(!newValue.equals(oldValue) && newValue.intValue()<192 ){
            if (Carte.getUniqueCarte().recupColonneTaille()>decor.getEnvironnement().getPrefRows()) {
                decor.updateMapX();
                decor.getEnvironnement().setPrefColumns(decor.getEnvironnement().getPrefColumns()+1);
            }
        }
    }

}
