package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Inventaire.Case;
import fr.iut.saeterraria.sae.Modele.Inventaire.Inventaire;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Coffre extends Bloc{
    private Inventaire contenuCoffre;

    public Coffre(String nom, String description, int typeBloc, int resistance, int codeobjet) {
        super(nom, description, resistance, codeobjet);
        this.contenuCoffre = new Inventaire(4,4);
    }

}
