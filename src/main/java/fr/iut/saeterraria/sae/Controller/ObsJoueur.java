package fr.iut.saeterraria.sae.Controller;


import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Personnages.Joueur;
import fr.iut.saeterraria.sae.Vue.SpriteJoueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObsJoueur implements ChangeListener<Number> {
    private SpriteJoueur sprite;
    private Clavier clavier;

    public ObsJoueur(SpriteJoueur sp, Clavier c){
        this.sprite = sp;
        this.clavier = c;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        clavier.update();
        sprite.mettreAJourSpriteJoueur(Joueur.getUniqueJoueur());
    }
    
}
