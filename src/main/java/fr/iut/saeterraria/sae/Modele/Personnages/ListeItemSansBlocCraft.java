package fr.iut.saeterraria.sae.Modele.Personnages;

import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocCraft;
import fr.iut.saeterraria.sae.Modele.Objets.Item;

public class ListeItemSansBlocCraft extends ListeItems{
    @Override
    public Item creerItem(String nom) {
        switch (nom) {
            case "Etabli" :
                return new BlocCraft();
        }
        return null;
    }
}
