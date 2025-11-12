package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Vue.VueBarreRaccourci;
import fr.iut.saeterraria.sae.Vue.VueInventaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ListenerInventaire implements ChangeListener<Boolean> {
    private VueInventaire vueInventaire;
    private VueBarreRaccourci vueBarreRaccourci;
    private int ligne,colonne;

    public ListenerInventaire(VueInventaire vueInventaire, VueBarreRaccourci vueBarreRaccourci, int ligne, int colonne) {
        this.vueInventaire=vueInventaire;
        this.vueBarreRaccourci = vueBarreRaccourci;
        this.ligne=ligne;
        this.colonne=colonne;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldboolean, Boolean newboolean) {
        if (this.ligne==0 && newboolean) {
            vueInventaire.updateElement(this.ligne,this.colonne);
            vueBarreRaccourci.updateElement(this.colonne);
        }
        else if (newboolean) {
            vueInventaire.updateElement(this.ligne,this.colonne);
        }
    }

}