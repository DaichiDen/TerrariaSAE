package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class Goblin extends Ennemi{

    public Goblin(int x, int y) {
        super(20, 20, x, y, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(),  Jeu.getUniqueJeu().getTaille1bloc()*2, 10, 8);
    }

    @Override
    public void action(int x, int y) {
        if(getDernièreAttaque()==getCooldown()) {
            if (this.getAttaque() - Joueur.getUniqueJoueur().getDef() > 0){
                Projectile p=new Fleche(this.getX(), this.getY());
                p.initialiserProjectile(Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
            }
            setDernièreAttaque(0);
        }
        setDernièreAttaque(getDernièreAttaque()+1);

    }
}
