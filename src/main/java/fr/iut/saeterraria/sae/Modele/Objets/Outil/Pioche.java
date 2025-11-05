package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pioche extends Outils {
    private int efficacite;
    public Pioche(String nom,String description, int efficacite, int codeobjet) {
        super(nom,description,codeobjet);
        this.efficacite=efficacite;
    }

    public int getEfficacite(){ return efficacite;}
    @Override
    public void action() {
        System.out.println("test");
    }
}
