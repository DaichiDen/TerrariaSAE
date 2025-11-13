package fr.iut.saeterraria.sae.Modele.Entites;

import fr.iut.saeterraria.sae.Modele.Jeu;

public class FabriqueDifficulteDifficile implements FabriqueDifficulteEnnemis {
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
        Ennemi mh2 = new MH(1003, 512);
        Ennemi goblin6 = new Goblin(1620, 448);
        Ennemi goblin7 = new Goblin(1408, 288);
        Ennemi ogre5 = new Ogre(1760, 299);
        Ennemi ogre6 = new Ogre(2373, 768);

        Jeu.getUniqueJeu().addEnnemis(ogre);
        Jeu.getUniqueJeu().addEnnemis(ogre2);
        Jeu.getUniqueJeu().addEnnemis(ogre3);
        Jeu.getUniqueJeu().addEnnemis(ogre4);
        Jeu.getUniqueJeu().addEnnemis(ogre5);
        Jeu.getUniqueJeu().addEnnemis(ogre6);

        Jeu.getUniqueJeu().addMobs(ogre);
        Jeu.getUniqueJeu().addMobs(ogre2);
        Jeu.getUniqueJeu().addMobs(ogre3);
        Jeu.getUniqueJeu().addMobs(ogre4);
        Jeu.getUniqueJeu().addMobs(ogre5);
        Jeu.getUniqueJeu().addMobs(ogre6);

        Jeu.getUniqueJeu().addEnnemis(goblin);
        Jeu.getUniqueJeu().addEnnemis(goblin2);
        Jeu.getUniqueJeu().addEnnemis(goblin3);
        Jeu.getUniqueJeu().addEnnemis(goblin4);
        Jeu.getUniqueJeu().addEnnemis(goblin5);
        Jeu.getUniqueJeu().addEnnemis(goblin6);
        Jeu.getUniqueJeu().addEnnemis(goblin7);

        Jeu.getUniqueJeu().addMobs(goblin);
        Jeu.getUniqueJeu().addMobs(goblin2);
        Jeu.getUniqueJeu().addMobs(goblin3);
        Jeu.getUniqueJeu().addMobs(goblin4);
        Jeu.getUniqueJeu().addMobs(goblin5);
        Jeu.getUniqueJeu().addMobs(goblin6);
        Jeu.getUniqueJeu().addMobs(goblin7);

        Jeu.getUniqueJeu().addEnnemis(mh);
        Jeu.getUniqueJeu().addMobs(mh);
        Jeu.getUniqueJeu().addEnnemis(mh2);
        Jeu.getUniqueJeu().addMobs(mh2);

    }
}
