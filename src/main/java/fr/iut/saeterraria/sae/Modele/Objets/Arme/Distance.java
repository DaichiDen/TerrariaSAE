package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Objets.Armes;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Entites.Projectile;

import java.util.ArrayList;

public class Distance extends Armes {

    private ArrayList<Projectile> projectiles;

    public Distance(String nom, String desc, int attaque, BlocConstruction blocConstruction, int codeobjet) {
        super(nom, desc, attaque, blocConstruction, codeobjet);
    }

    public Distance(String nom, String desc, int attaque,int codeobjet) {
        super(nom,desc,attaque,codeobjet);
    }



}

