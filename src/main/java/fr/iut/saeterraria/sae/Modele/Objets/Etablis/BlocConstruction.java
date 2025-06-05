package fr.iut.saeterraria.sae.Modele.Objets.Etablis;

import fr.iut.saeterraria.sae.Modele.Objets.Bloc;
import fr.iut.saeterraria.sae.Modele.Objets.Recette;

import java.util.HashMap;

public class BlocConstruction extends Bloc {
    HashMap<Integer, Recette> listeRecettes;

    public BlocConstruction(String nom, String desc, int typeBloc, int resistance, BlocConstruction provenance) {
        super(nom,desc,typeBloc,resistance,provenance);
        this.listeRecettes = new HashMap<>();
    }

    public BlocConstruction(String nom, String desc, int typeBloc, int resistance) {
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
