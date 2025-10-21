package fr.iut.saeterraria.sae.Modele.Objets.Arme;

import fr.iut.saeterraria.sae.Modele.Entites.Joueur;

public class Grappin extends Distance {

    public Grappin(String nom, String desc, int attaque,int codeobjet){

            super(nom,desc,attaque,codeobjet);
        }


    public int[] calculTrajectoireGrappin(int cibleX,int cibleY){
        int tab[] = new int[2];
        int ex = Joueur.getUniqueJoueur().getX();
        int ey = Joueur.getUniqueJoueur().getY();

        // Direction du tir
        int dx = cibleX - ex;
        int dy = cibleY - ey;

        // Normalisation du vecteur (dx, dy)
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        if (distance == 0) distance = 1; // éviter division par zéro

        // Vitesse initiale (puissance du tir)
        int puissance = 50;

        int vx = (int) (((float) dx / distance) * puissance);
        int vy = (int) (((float) dy / distance) * puissance);
        tab[0]=vx;
        tab[1]=vy;
        return tab;
    }

}
