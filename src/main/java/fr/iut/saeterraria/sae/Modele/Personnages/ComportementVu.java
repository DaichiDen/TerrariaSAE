package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.A_Star.Algo_A_Star;
import fr.iut.saeterraria.sae.Modele.A_Star.Node;

import java.util.List;

public class ComportementVu implements ComportementEnnemi{
    @Override
    public void agir(Ennemi ennemi) {
        Algo_A_Star pathfinding = new Algo_A_Star(ennemi.getJeu().getCarte());
        List<Node> path = pathfinding.trouverchemin(ennemi.getX()/32, ennemi.getY()/32, ennemi.getJeu().getJoueur().getX()/32, ennemi.getJeu().getJoueur().getY()/32);


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

            if(ennemi.peutEtreAtteint(ennemi.getJeu().getJoueur().getX()/32, ennemi.getJeu().getJoueur().getY()/32, ennemi.getRangeVue())){
                ennemi.action(ennemi.getJeu().getJoueur().getX(), ennemi.getJeu().getJoueur().getY());
            }

            // Tu peux gérer dy si les ennemis sautent ou volent
        }
        else{
            ennemi.setMarcheGauche(false);
            ennemi.setMarcheDroite(false);
            ennemi.action(ennemi.getJeu().getJoueur().getX(),ennemi.getJeu().getJoueur().getY());

        }
    }
}
