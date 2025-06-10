package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;

import java.util.ArrayList;

public class Distance extends Armes {

    private ArrayList<Projectile> projectiles;

    public Distance(String nom, String desc, double attaque, BlocConstruction blocConstruction) {
        super(nom, desc, attaque, blocConstruction);
    }



    @Override
    public void attaquer() {
        System.out.println("le caca est cuit");
        if(projectiles != null){
            projectiles.get(0).attaquer();
        }
    }
}
