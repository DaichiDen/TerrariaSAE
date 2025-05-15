package fr.iut.saeterraria.sae.Modele.Objets;

public class Soins extends Consommables{

    public Soins(String nom, String description, int efficacite) {
        super(nom, description, efficacite);
    }

    // Faudrait que item ou consommable ait attribut joueur pour que actionjoueur ici puisse augmenter les pv du joueur
    public void actionJoueur() {

    }
    //Créer une class Inventaire qui elle sera  lié au joueur?
}
