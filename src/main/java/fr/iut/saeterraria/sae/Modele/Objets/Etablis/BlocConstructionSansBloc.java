package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Objets.ListeItemSansBlocCraft;

public class BlocConstructionSansBloc extends BlocConstruction{
    public BlocConstructionSansBloc() {
        super("Bloc craft sans bloc", "", 0, 100, new ListeItemSansBlocCraft());
    }
}
