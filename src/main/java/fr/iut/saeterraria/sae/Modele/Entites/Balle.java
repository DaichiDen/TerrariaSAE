package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Balle extends Projectile{
    private Jeu jeu= Jeu.getUniqueJeu();

    public Balle(int xJoueur, int yJoueur) {
        super(xJoueur, yJoueur, 8, 16, 16);
        arretJeu();
    }

    @Override
    public int getType() {
        return 80;
    }

    @Override
    public void action() {
        jeu.setArretTemps(false);
    }

    public void arretJeu(){
        jeu.setArretTemps(true);
    }

    public boolean affecteTemps(){
        return true;
    }

}
