package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    private Jeu jeu;
    private Map map;
    @BeforeEach void Test(){
        jeu = new Jeu("Stong");
        map = jeu.getCarte();
        jeu.getJoueur().ajouterItem(jeu.getItems().get(3),2); // On place 2 bois dans l'inventaire du joueur [0][0] (ligne 0 colonne 0)
    }
    @Test
    void poser() {
        jeu.getJoueur().setX(736);
        jeu.getJoueur().setY(224);

        jeu.getJoueur().setMainCourante(0);
        jeu.getJoueur().poser(736/32,256/32);
        System.out.println(jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getQuantite());

        //Test1 où le posage est validé
        assertTrue(jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getQuantite()==1); // on vérifie que le bloc qu'on a posé s'est retiré de l'inventaire
        assertTrue(jeu.getCarte().getCase(256/32,736/32)==3); // on vérifie que au coordonnés où le joueur à clické le bloc de bois a été posé (get case renvoie un id , l'id du bois est 3)



        //Test où le posage est pas validé
        jeu.getJoueur().poser(128/32,736/32);// valeurs absurdes qui sont en dehors de la portée du joueur
        assertTrue(jeu.getJoueur().getInventaire().getInventaireJoueur()[0][jeu.getJoueur().getMainCourante()].getQuantite()==1); // On a posé un des 2 blocs de bois , donc si je fais poser et que la quantité n'est pas décrementé alors le bloc est pas posé
        assertTrue(jeu.getCarte().getCase(128/32,736/32)==0); // On a pas posé de bloc, donc la map reste inchangé (id 0 = bloc invisible)



    }

    @Test
    void craftItem() {
        var joueur = jeu.getJoueur();
        joueur.craftItem(jeu.getItems().get(12)); // craft de  l'établi qui nécessite 2 bois

        // On s'attend que le craft item enlève les bois de l'inventaire, donc que la case 0 0 ne contient plus d'item (soit que l'id vaut 0)
        assertEquals(0, joueur.getInventaire().getInventaireJoueur()[0][0].getItem().getCodeObjet());

        // On s'attend que le craft item ajoute l'établi dans l'inventaire, donc que la case 0 1 contient l'établi qui est d'id 12
        assertEquals(jeu.getItems().get(12).getCodeObjet(),jeu.getJoueur().getInventaire().getInventaireJoueur()[0][1].getItem().getCodeObjet());
    }
}