package fr.iut.saeterraria.sae.Modele.Objets.Etablis;
import fr.iut.saeterraria.sae.Modele.Personnages.ListeItemBlocCraft;

public class BlocCraft extends BlocConstruction{
    public BlocCraft() {
        super("Etabli","Un établi qui permet la fabrication d'objets", 1, 12, new ListeItemBlocCraft());
    }
}
