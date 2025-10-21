package fr.iut.saeterraria.sae.Modele.Objets;


import fr.iut.saeterraria.sae.Modele.Objets.Arme.*;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.*;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.*;

public enum ListeItems {
    Ciel(0,new Bloc("Ciel","Incassable+traversable fond",10,0)),
    Terre_Haute(1, new Bloc("Terre Haute","Bloc commun qui recouvre le monde", 1,1)),
    Terre_Basse(2, new Bloc("Terre Basse","Bloc commun qui recouvre le sol du monde", 1,2)),
    Bois(3, new Bloc("Bois","Element indispensable, base de créativité", 1,3)),
    Minerai_Charbon(4, new Bloc("Minerai Charbon","Bloc contenant plusieurs charbons", 2,4)),
    Pierre(5, new Bloc("Pierre","Bloc basique de pierre commun dans les sous-sol", 2,5)),
    Minerai_Fer(6, new Bloc("Minerai Fer","Métal commun de Fer", 3,6)),
    Minerai_DELJCCium(7, new Bloc("Minerai DELJCCium","", 4,7)),
    Pique(8, new Bloc("Pique","", 10,8)),
    BedRock(9, new Bloc("BedRock","Incassable", 10,9)),
    Noir(10, new Bloc("Noir","Incassable+traversable fond", 10,10)),

    ConstructionSansBloc(11, new BlocConstructionSansBloc()),
    Etabli(12, new BlocCraft()),
    Forge(13, new BlocForge()),
    Four(14, new BlocFour()),

    Planche_Bois(15, new Bloc("Planche de bois","", 1,15)),
    Toit_Bois_Gauche(16, new Bloc("Toit_bois_gauche","", 1,16)),
    Toit_Bois_Droite(17, new Bloc("Toit bois droite", "", 1,17)),
    Mur_Bois_Fonce(18, new Bloc("Fonce bois mur", "", 0,18)),
    Feuilles(19, new Bloc("Feuilles", "", 1,19)),

    Coffre(20, new Coffre("Coffre", "", 3, 3,20)),
    Charbon(21, new Item("Charbon","Permet d'alimenter le four et la forge en chaleur",1,21)),
    Fer(22, new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1,22)),
    DELJCCium(23, new Item("DELJCCium", "", 1,23)),
    Seau_Eau(63, new Item("Seau eau", "", 1,63)),

    Arquebuse(79, new Distance("Arquebuse","Etrange objet qui semble ralentir le temps",5,79)),

    Balle_Plonb(80, new Item("Balle en plomb","Un projectile qui peut être utlisé",1,80)),
    Grappin(81, new Grappin("Grappin","Permet de s'accrocher aux surfaces",0,81)),


    Boule_De_Feu(82, new Item("Boule de feu","Une boule de feu qui explose à l'impact",1,82));

    private int id;
    private Item item;

    ListeItems(int id, Item item) {
        this.id = id;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public static Item getItemParId(int i) {
        for(ListeItems item : values()) {
            if(item.getId()==i) {
                return item.getItem();
            }
        }
        return null;
    }
}
