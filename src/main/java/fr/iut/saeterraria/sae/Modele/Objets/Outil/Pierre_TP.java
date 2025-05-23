package fr.iut.saeterraria.sae.Modele.Objets.Outil;

import fr.iut.saeterraria.sae.Modele.Objets.Outils;

public class Pierre_TP extends Outils {

    public Pierre_TP() {
        super("Waypoint","Cet objet semble détenir le pouvoir de se téléporter...");
    }

    @Override
    public void action() {
        System.out.println("baaaa");
    }
}
