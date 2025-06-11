package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Entite;
import fr.iut.saeterraria.sae.Vue.Fond;
import fr.iut.saeterraria.sae.Vue.SpriteJoueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.TilePane;

public class ObsMap implements ChangeListener<Number> {

    private Jeu jeu;
    private Fond fond;

    public ObsMap(Jeu jeu, Fond fond){
        this.jeu = jeu;
        this.fond = fond;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        System.out.println(newValue);
        if(!newValue.equals(oldValue) ){
            fond.updateMap(jeu.getJoueur().getXMax(),jeu.getJoueur().getYMax());
        }
    }

}
