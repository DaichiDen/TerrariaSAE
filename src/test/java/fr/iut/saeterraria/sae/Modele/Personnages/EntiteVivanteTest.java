package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class EntiteVivanteTest {
   private Map map;
   private Jeu jeu;
    @BeforeEach void test(){
        map = new Map();
        jeu = new Jeu("Joueur");
    }

    @Test
    void testBloqueVerticalEnTombant() {


        var joueur = jeu.getJoueur();
        joueur.setX(64);
        joueur.setY(0);
        System.out.println(joueur.getVitesseY() + "vitesseY");
        System.out.println(joueur.getY() +"y");

        for(int i=0;i<1000;i++){ // mettre à jour est appelé chaque frame dans le game loop, on fait ici une boucle for 1000 fois pour être sur qu'il tombe assez
            joueur.mettreAJour();
            System.out.println(joueur.getVitesseY()+ "vitesseY");
            System.out.println(joueur.getY()+"y");
        }
       // applique les mouvements + collision

        assertEquals(448, joueur.getY(), "Le joueur devrait être posé sur le bloc."); // 448 c'est le seuil du sol dans la vue quand le joueur spawn en 0,2 côté modèle (0,64 côté vue)(en l'air coin gauche haut)
        assertEquals(0,joueur.getVitesseY());

    }

    @Test
    void peutEtreAtteint() {
        var joueur = jeu.getJoueur();
        joueur.setX(480);
        joueur.setY(448);

        assertFalse(joueur.peutEtreAtteint(576/32,448/32,2)); // Test 1 , on place le joueur à 3 blocs de différence du bloc souhaité, le teste doit retourner false car la range est de 2

        joueur.setX(512);
        joueur.setY(448);

        assertTrue(joueur.peutEtreAtteint(576/32,448/32,2)); // Test 2, on place le joueur à 2 blocs de différence du bloc souhaité, le test doit retourner true car on mets une range de 2 et il est à 2 blocs de différence
    }
}