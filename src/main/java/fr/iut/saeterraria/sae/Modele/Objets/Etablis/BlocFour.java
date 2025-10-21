package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Personnages.ListeItemFour;
import fr.iut.saeterraria.sae.Modele.Personnages.ListeItems;

public class BlocFour extends BlocConstruction{

    public BlocFour() {
        super("Four","Permet de fondre et cuire ses objets", 1, 14, new ListeItemFour());
    }
}
