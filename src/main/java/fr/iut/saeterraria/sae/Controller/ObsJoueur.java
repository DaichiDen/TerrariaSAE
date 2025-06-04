package fr.iut.saeterraria.sae.Controller;


import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Vue.SpriteJoueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsJoueur implements ChangeListener<Number> {
    private Jeu jeu;
    private SpriteJoueur sprite;
    private Clavier clavier;

    public ObsJoueur(Jeu jeu, SpriteJoueur sp, Clavier c){
        this.jeu = jeu;
        this.sprite = sp;
        this.clavier = c;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        clavier.update();
        sprite.mettreAJourSpriteJoueur(jeu.getJoueur());
    }
    
}
