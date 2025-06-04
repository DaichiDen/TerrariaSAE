package fr.iut.saeterraria.sae.Controller;

import fr.iut.saeterraria.sae.Vue.VueHotbar;
import fr.iut.saeterraria.sae.Vue.VueInventaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ListenerInventaire implements ChangeListener<Boolean> {
    private VueInventaire vueInventaire;
    private VueHotbar vueHotbar;
    private int ligne,colonne;

    public ListenerInventaire(VueInventaire vueInventaire, VueHotbar vueHotbar, int ligne, int colonne) {
        this.vueInventaire=vueInventaire;
        this.vueHotbar=vueHotbar;
        this.ligne=ligne;
        this.colonne=colonne;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldboolean, Boolean newboolean) {
        if (this.ligne==0 && newboolean) {
            vueInventaire.updateElement(this.ligne,this.colonne);
            vueHotbar.updateElement(this.colonne);
        }
        else if (newboolean) {
            vueInventaire.updateElement(this.ligne,this.colonne);
        }
    }

}