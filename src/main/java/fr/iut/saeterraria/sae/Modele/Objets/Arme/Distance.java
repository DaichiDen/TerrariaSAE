package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;

import java.util.ArrayList;

public class Distance extends Armes {

    private ArrayList<Projectile> projectiles;

    public Distance(String nom, String desc, int attaque, BlocConstruction blocConstruction) {
        super(nom, desc, attaque, blocConstruction);
    }

    public Distance(String nom, String desc, int attaque) {
        super(nom,desc,attaque);
    }


    @Override
    public void attaquer() {
        System.out.println("le caca est cuit");

        }
    }

