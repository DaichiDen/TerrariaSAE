package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Goblin extends Ennemi{

    public Goblin(String nom, int vieMax, int energieMax, int x, int y, int def, Jeu jeu, int attaque, int tailleL, int tailleH) {
        super(nom, vieMax, energieMax, x, y, def, jeu, attaque, tailleL, tailleH);
    }

    @Override
    public void action(int x, int y, int range) {

        if(getDernièreAttaque()==getCooldown()) {
            if (this.getAttaque() - getJeu().getJoueur().getDef() > 0){
                tirerProjectile(new Projectile("Flèche",getJeu(),this.getX(),this.getY(),3,"fleche", 16,16),super.getJeu().getJoueur().getX(),super.getJeu().getJoueur().getY());
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque()+1);

    }
}
