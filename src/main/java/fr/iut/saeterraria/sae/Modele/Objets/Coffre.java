package fr.iut.saeterraria.sae.Modele.Objets;

import fr.iut.saeterraria.sae.Modele.Personnages.Case;
import fr.iut.saeterraria.sae.Modele.Personnages.Inventaire;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Coffre extends Bloc{
    private Inventaire contenuCoffre;

    public Coffre(String nom, String description, int typeBloc, int resistance) {
        super(nom, description, resistance);
        this.contenuCoffre = new Inventaire(4,4);
    }

    public ObservableList<Case> getContenu() {
        return contenuCoffre.getInventaireJoueur();
    }

    public void addItem(Item item, int quantite) {
        contenuCoffre.ajoutInventaire(item, quantite);
    }

    public ArrayList<Case> findItem(Item item) {
        return contenuCoffre.findItem(item);
    }
}
