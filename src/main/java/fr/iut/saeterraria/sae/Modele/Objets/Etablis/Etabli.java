package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.ElementRecette;
import fr.iut.saeterraria.sae.Modele.Objets.Item;
import fr.iut.saeterraria.sae.Modele.Objets.Recette;
import javafx.beans.property.StringProperty;

import java.util.HashMap;

public class Etabli extends Bloc {
    HashMap<Integer, Recette> listeRecettes;

    public Etabli(String nom, String desc, int typeBloc, int resistance) {
        super(nom,desc,typeBloc,resistance);
        this.listeRecettes = new HashMap<>();
    }

    public void addRecette(Integer idItem, Recette recette){
        this.listeRecettes.put(idItem, recette);
    }

    public HashMap<Integer, Recette> getListeRecette(){
        return this.listeRecettes;
    }

    public Recette getRecette(Integer idItem){
        return this.listeRecettes.get(idItem);
    }
}
