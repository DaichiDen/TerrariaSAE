package fr.iut.saeterraria.sae.Modele;

import fr.iut.saeterraria.sae.Modele.Objets.*;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.Etabli;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Hache;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pelle;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
import fr.iut.saeterraria.sae.Modele.Personnages.*;
import fr.iut.saeterraria.sae.Modele.Map.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

// Environnement du jeu
public class Jeu {

    private Map carte;
    private Joueur joueur;
    private ArrayList<Ennemi> mobs;
    private ArrayList<PNJ> pNJ;
    private HashMap<Integer, Item> items; // Associe chaque item (outil) avec son id (bloc de 0 à 20 par exemple)
    public Jeu(String nomJoueur){
        carte = new Map();
        joueur = new Joueur(nomJoueur);
        mobs = new ArrayList<>();
        pNJ = new ArrayList<>();
        items = new HashMap<>();
        initialiseItems();
    }

    public void addMobs(Ennemi ennemi) {
        mobs.add(ennemi);
    }
    public void removemob(Ennemi ennemi){
        mobs.remove(ennemi);
    }

    public void addPNJ(PNJ pnj) {
        pNJ.add(pnj);
    }
    public void removePNJ(PNJ pnj) {
        pNJ.remove(pnj);
    }

    public boolean estVivant(Entite entite){
        return entite.getBarreVie().getVie()>0;
    }

    public Map getCarte(){
        return carte;
    }
    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Ennemi> getMobs() {
        return mobs;
    }

    public ArrayList<PNJ> getpNJ() {
        return pNJ;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    private void initialiseItems() { //Ajouter une range d"id pour item pas obtenable
        // Blocs
        items.put(1, new Bloc("Terre Haute","Bloc commun qui recouvre le monde",1,1));
        items.put(2, new Bloc("Terre Basse","Bloc commun qui recouvre le ",1,1));
        items.put(3, new Bloc("Bois","",1,3));
        items.put(4, new Bloc("Minerai Charbon","",2,3));
        items.put(5, new Item("Charbon","",1));
        items.put(6, new Bloc("Pierre","",2,5));
        items.put(7, new Bloc("Minerai Fer","",3,7));
        items.put(8, new Item("Fer","",1));
        items.put(9, new Bloc("Glace","",1,2));

        //Bloc outil
        items.put(10, new Etabli("Etabli","Un établi qui permet la fabrication d'objets",1,3));

        // Outils
        items.put(50,new Pelle("Pelle de bois","Une pelle en bois ordinaire, accélère la vitesse pour creuser des objets"));
        items.put(51,new Hache("Hache de bois",""));
        items.put(52,new Pioche("Pioche de bois",""));
        items.put(53,new Pelle("Pelle de pierre",""));
        items.put(54,new Hache("Hache de pierre",""));
        items.put(55,new Pioche("Pioche de pierre",""));
        items.put(56,new Pelle("Pelle de fer",""));
        items.put(57,new Hache("Hache de fer",""));
        items.put(58,new Pioche("Pioche de fer",""));
        items.put(59,new Pelle("Pelle de DELJCCium","Une pelle en DELJCCium exclusive, accélère fortement la vitesse pour creuser des objets"));
        items.put(60,new Hache("Hache de DELJCCium",""));
        items.put(61,new Pioche("Pioche de DELJCCium",""));

        // Armures
        items.put(62,new Armure("Casque en fer","",2));
        items.put(63,new Armure("Casque en DELJCCium","",3));
        items.put(64,new Armure("Plastron en fer","",6));
        items.put(65,new Armure("Plastron en DELJCCium","",9));
        items.put(66,new Armure("Jambière en fer","",4));
        items.put(67,new Armure("Jambière en DELJCCium","",6));
        items.put(68,new Armure("Botte en fer","",3));
        items.put(69,new Armure("Botte en DELJCCium","",5));

        // Armes

    }
    
}
