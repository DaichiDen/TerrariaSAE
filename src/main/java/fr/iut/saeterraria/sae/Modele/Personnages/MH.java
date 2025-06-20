package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class MH extends Ennemi{

    public MH(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH);
    }

    @Override
    public void action(int x, int y, int range) {


    }
}
