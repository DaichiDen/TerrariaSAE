package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class FabriqueDifficulteNormale implements FabriqueDifficulteEnnemis {
    @Override
    public void creerJeu() {
            Ennemi ogre = new Ogre( 3000, 0);
            Ennemi ogre2 = new Ogre(1340, 1340);
            Ennemi ogre3 = new Ogre(4962, 1376);
            Ennemi ogre4 = new Ogre(3068, 1600);
            Ennemi goblin = new Goblin(5000, 0);
            Ennemi goblin2 = new Goblin(1456, 1728);
            Ennemi goblin3 = new Goblin(2959, 1088);
            Ennemi goblin4 = new Goblin(5238, 1760);
            Ennemi goblin5 = new Goblin(4544, 1632);
            Ennemi mh = new MH(4500, 0);

            Jeu.getUniqueJeu().addEnnemis(ogre);
            Jeu.getUniqueJeu().addEnnemis(ogre2);
            Jeu.getUniqueJeu().addEnnemis(ogre3);
            Jeu.getUniqueJeu().addEnnemis(ogre4);
            Jeu.getUniqueJeu().addMobs(ogre);
            Jeu.getUniqueJeu().addMobs(ogre2);
            Jeu.getUniqueJeu().addMobs(ogre3);
            Jeu.getUniqueJeu().addMobs(ogre4);

            Jeu.getUniqueJeu().addEnnemis(goblin);
            Jeu.getUniqueJeu().addEnnemis(goblin2);
            Jeu.getUniqueJeu().addEnnemis(goblin3);
            Jeu.getUniqueJeu().addEnnemis(goblin4);
            Jeu.getUniqueJeu().addEnnemis(goblin5);
            Jeu.getUniqueJeu().addMobs(goblin);
            Jeu.getUniqueJeu().addMobs(goblin2);
            Jeu.getUniqueJeu().addMobs(goblin3);
            Jeu.getUniqueJeu().addMobs(goblin4);
            Jeu.getUniqueJeu().addMobs(goblin5);

            Jeu.getUniqueJeu().addEnnemis(mh);
            Jeu.getUniqueJeu().addMobs(mh);

        }

}
