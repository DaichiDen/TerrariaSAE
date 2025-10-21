package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Personnages.ListeItemBlocForge;

public class BlocForge extends BlocConstruction{
    public BlocForge() {
        super("Forge","Un établi qui permet la fabrication d'objets", 1, 13, new ListeItemBlocForge());
    }
}
