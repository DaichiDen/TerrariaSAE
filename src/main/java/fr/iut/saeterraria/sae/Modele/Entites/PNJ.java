package fr.iut.saeterraria.sae.Modele.Entites;
import fr.iut.saeterraria.sae.Modele.Jeu;
import fr.iut.saeterraria.sae.Modele.Map.Carte;

import java.util.ArrayList;

public class PNJ extends EntiteVivante{

    private ArrayList<String> dialogue;
    public PNJ(int x, int y, Carte carte, Jeu jeu, int tailleL, int tailleH) {

        super(20, 20, 10, x, y, 2, 0,0, tailleL, tailleH, 1,1);

        dialogue = new ArrayList<String>();
    }

    @Override
    public void action(int x, int y) {

    }
}
