package fr.iut.saeterraria.sae.Modele.Entites.Comportements;

import fr.iut.saeterraria.sae.Modele.A_Star.Algo_A_Star;
import fr.iut.saeterraria.sae.Modele.A_Star.Node;
import fr.iut.saeterraria.sae.Modele.Map.Carte;
import fr.iut.saeterraria.sae.Modele.Entites.Ennemi;
import fr.iut.saeterraria.sae.Modele.Entites.Joueur;

import java.util.List;
/*
Déplacement vers le joueur lorsqu'un ennemu voit le joueur dans la portée de détection
 */
public class ComportementVu implements ComportementEnnemi {
    @Override
    public void agir(Ennemi ennemi) {
        Algo_A_Star pathfinding = new Algo_A_Star(Carte.getUniqueCarte());
        List<Node> path = pathfinding.trouverchemin(ennemi.getX()/32, ennemi.getY()/32, Joueur.getUniqueJoueur().getX()/32, Joueur.getUniqueJoueur().getY()/32);

        if (!path.isEmpty() && path.size()>1) {
            Node nextStep = path.get(1); // [0] = position actuelle
            int dx = nextStep.x - (ennemi.getX() / 32);
            int dy = nextStep.y - (ennemi.getY() / 32);

            if (dx < 0) {
                ennemi.setMarcheGauche(true);
                ennemi.setMarcheDroite(false);
            } else if (dx > 0) {
                ennemi.setMarcheGauche(false);
                ennemi.setMarcheDroite(true);
            }
            if(dy < 0) {
                ennemi.sauter();
            }

            if(Carte.getUniqueCarte().peutEtreAtteint(Joueur.getUniqueJoueur().getX()/32, Joueur.getUniqueJoueur().getY()/32, ennemi.getPorteeVue(), ennemi)){
                ennemi.action(Joueur.getUniqueJoueur().getX(), Joueur.getUniqueJoueur().getY());
            }

            // Tu peux gérer dy si les ennemis sautent ou volent
        }
        else{
            ennemi.setMarcheGauche(false);
            ennemi.setMarcheDroite(false);
            ennemi.action(Joueur.getUniqueJoueur().getX(),Joueur.getUniqueJoueur().getY());

        }
    }
}
