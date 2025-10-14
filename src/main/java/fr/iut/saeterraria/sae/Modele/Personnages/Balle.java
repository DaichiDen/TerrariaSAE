package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Balle extends Projectile{
    private Jeu jeu= Jeu.getUniqueJeu();

    public Balle(int xJoueur, int yJoueur) {
        super("balle", xJoueur, yJoueur, 8, 16, 16);
        arretJeu();
    }


    @Override
    public void action() {
        jeu.setArretJeu(false);
    }

    public void arretJeu(){
        jeu.setArretJeu(true);
    }

}
