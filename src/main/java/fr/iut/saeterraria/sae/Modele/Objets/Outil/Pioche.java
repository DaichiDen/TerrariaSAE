package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pioche extends Outils {
    private int efficacite;
    public Pioche(String nom,String description, int efficacite) {
        super(nom,description);
        this.efficacite=efficacite;
    }

    public Pioche(String nom, String description, int efficacite, BlocConstruction blocConstruction) {
        super(nom,description, blocConstruction);
        this.efficacite=efficacite;
    }
    public int getEfficacite(){ return efficacite;}
    @Override
    public void action() {
        System.out.println("test");
    }
}
