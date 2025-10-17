package fr.iut.saeterraria.sae.Modele.Entites.Comportements;

import fr.iut.saeterraria.sae.Modele.Entites.Ennemi;

public class ComportementPasVu implements ComportementEnnemi {
    @Override
    public void agir(Ennemi ennemi) {
        int aleaComp = (int) (Math.random()*11);
        if(aleaComp < 3){
            ennemi.setMarcheDroite(true);
            ennemi.setMarcheGauche(false);
        } else if (aleaComp < 6) {
            ennemi.setMarcheDroite(false);
            ennemi.setMarcheGauche(true);
        } else if (aleaComp < 9) {
            ennemi.setMarcheDroite(false);
            ennemi.setMarcheGauche(false);

        }
    }
}
