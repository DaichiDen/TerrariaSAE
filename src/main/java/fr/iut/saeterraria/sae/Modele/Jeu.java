package fr.iut.saeterraria.sae.Modele;

import fr.iut.saeterraria.sae.Modele.Objets.*;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.DashingKatana;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.Distance;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.Epee;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pierre_TP;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pioche;
import fr.iut.saeterraria.sae.Modele.Personnages.*;
import fr.iut.saeterraria.sae.Modele.Map.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

// Environnement du jeu
public class Jeu {

    private Map carte;
    private Joueur joueur;
    private ArrayList<Ennemi> ennemis;
    private ArrayList<PNJ> pNJ;
    private HashMap<Integer, Item> items; // Associe chaque item (outil) avec son id (bloc de 0 à 20 par exemple)
    private ObservableList<EntiteVivante> mobs;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;

    public final static int taille1bloc = 32;

    public Jeu(String nomJoueur){
        items = new HashMap<>();
        initialiseItems();
        initializeRecettes();
        initializeBlocConstruction();
        carte = new Map();
        joueur = new Joueur(nomJoueur, this, (Pierre_TP) items.get(49), taille1bloc, taille1bloc*2);
        ennemis = new ArrayList<>();
        pNJ = new ArrayList<>();
        mobs = FXCollections.observableArrayList(ennemis);
        projectiles= new ArrayList<>();
        liste_projectiles = FXCollections.observableArrayList(projectiles);

    }

    public int getTaille1bloc(){
        return taille1bloc;
    }

    public ArrayList<Projectile> getListe_projectiles() {
        return projectiles;
    }
    public ObservableList<Projectile> getListe_projectilesObservable() {
        return liste_projectiles;
    }

    public void màjProjectiles() {
        if (this.getListe_projectiles() != null) {

            for (int i = getListe_projectiles().size() - 1; i >= 0; i--) {
                Projectile p = getListe_projectiles().get(i);

                if (getJoueur().isTimeStop()) {
                    // Si timeStop activé, on met à jour uniquement les balles
                    if (p.getType().equals("balle")) {

                        p.setX(p.getX() + (int) p.getForceX());

                        p.setY(p.getY() + (int) p.getForceY());

                        if (p.collisionVerticale() || p.collisionHorizontale()) {
                            p.setActif(false);
                            getListe_projectiles().remove(i);
                        }
                    }

                } else {
                    // timeStop désactivé, on met à jour tous les projectiles normalement
                    p.setX(p.getX() + (int) p.getForceX());

                    if (!p.getNom().equals("Balle en plomb")) {
                        p.setForceY(p.getForceY() + p.getGravité());
                    }

                    p.setY(p.getY() + (int) p.getForceY());

                    if (p.collisionVerticale() || p.collisionHorizontale()) {
                        p.setActif(false);
                        getListe_projectiles().remove(i);
                    }
                }
            }
        }
    }

    public ObservableList<EntiteVivante> getMobs() {
        return mobs;
    }
    public void addMobs(EntiteVivante entite){
        mobs.add(entite);
    }
    public void removeMob(Entite entite){
        mobs.remove(entite);
    }

    public void addEnnemis(Ennemi ennemi) {
        ennemis.add(ennemi);
    }
    public void removeEnnemi(Ennemi ennemi){
        ennemis.remove(ennemi);
    }

    public Item getItem(String nom){
        for (Item item : items.values()) {
            if(item.getName().equals(nom)){
                return item;
            }
        }
        return null;
    }

    public void addPNJ(PNJ pnj) {
        pNJ.add(pnj);
    }
    public void removePNJ(PNJ pnj) {
        pNJ.remove(pnj);
    }

    public boolean estVivant(EntiteVivante entite){
        return entite.getBarreVie().getVie()>0;
    }

    public Map getCarte(){
        return carte;
    }
    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public ArrayList<PNJ> getpNJ() {
        return pNJ;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    private void initialiseItems() {//Ajouter une range d"id pour item pas obtenable
        items.put(0, new Bloc("Ciel","Ciel du monde",0,0));
        // Blocs

        items.put(1, new Bloc("Terre Haute","Bloc commun qui recouvre le monde",1,1));
        items.put(2, new Bloc("Terre Basse","Bloc commun qui recouvre le sol du monde",1,1));
        items.put(3, new Bloc("Bois","Element indispensable, base de créativité",1,1));
        items.put(4, new Bloc("Minerai Charbon","Bloc contenant plusieurs charbons",2,3));
        items.put(5, new Bloc("Pierre","Bloc basique de pierre commun dans les sous-sol",2,2));
        items.put(6, new Item("Charbon","Permet d'alimenter le four et la forge en chaleur",1));
        items.put(7, new Bloc("Minerai Fer","Métal commun de Fer",3,3));
        items.put(8, new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1));
        items.put(9, new Bloc("Minerai DELJCCium","",3,4));
        items.put(10, new Item("DELJCCium", "", 1));
        items.put(11,new BlocConstruction("ConstructionSansBloc","",0,0));
        items.put(12, new BlocConstruction("Etabli","Un établi qui permet la fabrication d'objets",1,1, (BlocConstruction) items.get(11)));
        items.put(13, new BlocConstruction("Forge","Un établi qui permet la fabrication d'objets",1,3, (BlocConstruction) items.get(12) ));
        items.put(14, new Item("","",1));
        items.put(15, new BlocConstruction("Four","Permet de fondre et cuire ses objets",1,2,(BlocConstruction) items.get(12)));

        items.get(8).setProvenance((BlocConstruction) items.get(15));
        items.get(10).setProvenance((BlocConstruction) items.get(15));
        items.put(16, new Bloc("Pique","",1,10));
        items.put(17, new Bloc("BedRock","Incassable",1,10));
        items.put(18,new Bloc("FondNoir","Incassable+traversable fond",1,10));
        items.put(19, new Bloc("Planche de bois","",1,1));
        items.put(20, new Bloc("Toit_bois_gauche","",1,1));
        items.put(21, new Bloc("Toit bois droite", "", 1, 1));
        items.put(22, new Bloc("Fonce bois mur", "", 0, 0));
        items.put(23, new Bloc("Feuilles", "", 1, 1));

        //Bloc outil
        items.put(24, new Coffre("Coffre", "", 1, 3,(BlocConstruction) items.get(12)));

        for (int i = 25; i < 49; i++) {
            items.put(i,new Item("","",1));

        }

        // Outils
        items.put(49, new Pierre_TP());
        items.put(50, new Item("","",1));
        items.put(51, new Pioche("Pioche de bois","",2,(BlocConstruction) items.get(12)));
        items.put(52, new Pioche("Pioche de pierre","",3,(BlocConstruction) items.get(12)));
        items.put(53, new Pioche("Pioche de fer","",4,(BlocConstruction) items.get(13)));
        items.put(54 ,new Pioche("Pioche DELJCCium","",5,(BlocConstruction) items.get(13)));

        for (int i = 55; i < 62; i++) {
            items.put(i, new Item("","",1));
        }

        items.put(62, new Item("Seau vide", "", 1,(BlocConstruction) items.get(13)));
        items.put(63, new Item("Seau eau", "", 1,(BlocConstruction) items.get(13)));

        // Armures

        items.put(64, new Armure("Casque en fer","",2, (BlocConstruction) items.get(13),1));
        items.put(65, new Armure("Casque en DELJCCium","",3,(BlocConstruction) items.get(13),1));
        items.put(66, new Armure("Plastron en fer","",6,(BlocConstruction) items.get(13),2));
        items.put(67, new Armure("Plastron en DELJCCium","",9,(BlocConstruction) items.get(13),2));
        items.put(68, new Armure("Jambière en fer","",4,(BlocConstruction) items.get(13),3));
        items.put(69, new Armure("Jambière en DELJCCium","",6,(BlocConstruction) items.get(13),3));
        items.put(70, new Armure("Botte en fer","",3,(BlocConstruction) items.get(13),4));
        items.put(71, new Armure("Botte en DELJCCium","",5,(BlocConstruction) items.get(13),4) );

        // Armes
        items.put(72, new DashingKatana("Katana étrange","Ce Katana semble pouvoir octroyer la capacité à son détenteur de se déplacer à la vitesse du son",10));
        items.put(73, new Epee("Epée en Bois","",1,(BlocConstruction) items.get(12)));
        items.put(74, new Epee("Epée en Pierre","",3,(BlocConstruction) items.get(12)));
        items.put(75, new Epee("Epée en Fer","",4,(BlocConstruction) items.get(13)));
        items.put(76, new Epee("Epée en DELJCCium","",5,(BlocConstruction) items.get(13)));
        // Autres items

        items.put(77, new Item("Flèche","Flèche",1,(BlocConstruction) items.get(13)));
        items.put(78, new Distance("Arc en bois","Un vieil arc usé",10,(BlocConstruction) items.get(12)));
        items.put(79, new Distance("Arquebuse","Etrange objet qui semble ralentir le temps",5));
        items.put(80, new Item("Balle en plomb","Un projectile qui peut être utlisé ",1, (BlocConstruction) items.get(13)));
    }

    private void initializeRecettes() {

        // Lingot de fer : 1 Minérai de fer et 1 charbon et 1 four
        items.get(8).addInRecette(new ElementRecette(items.get(6),1));
        items.get(8).addInRecette(new ElementRecette(items.get(4),1));

        // Lingot de DELJCCium : 1 Minérai de DELJCCium et 1 charbon et 1 four
        items.get(10).addInRecette(new ElementRecette(items.get(16),1));
        items.get(10).addInRecette(new ElementRecette(items.get(4),1));

        //Etabli : 2 bois
        items.get(12).addInRecette(new ElementRecette(items.get(3),2));

        //Four : 8 Pierre
        items.get(15).addInRecette(new ElementRecette(items.get(6),8));

        // Forge : 9 fer et 1 seau
        items.get(13).addInRecette(new ElementRecette(items.get(8),9));
        items.get(13).addInRecette(new ElementRecette(items.get(62),1));

        // Alambique : 2 seau et 3 pierres
        items.get(14).addInRecette(new ElementRecette(items.get(62),2));
        items.get(14).addInRecette(new ElementRecette(items.get(6),3));

        // Coffre : 6 bois
        items.get(24).addInRecette(new ElementRecette(items.get(3),6));

        // Pioche de bois : 4 bois
        items.get(51).addInRecette(new ElementRecette(items.get(3),4));
        // Pioche de pierre : 3 pierre + 1 bois
        items.get(52).addInRecette(new ElementRecette(items.get(6),3));
        items.get(52).addInRecette(new ElementRecette(items.get(3),1));
        // Pioche de fer : 3 fer + 1 bois + forge
        items.get(53).addInRecette(new ElementRecette(items.get(8),3));
        items.get(53).addInRecette(new ElementRecette(items.get(3),1));
        // Pioche en DELJCCnium : 3 DELJCCium + 1 bois + forge
        items.get(54).addInRecette(new ElementRecette(items.get(10),3));
        items.get(54).addInRecette(new ElementRecette(items.get(3),1));

        // Casque en fer + forge
        items.get(64).addInRecette(new ElementRecette(items.get(8),5));
        // Plastron en fer + forge
        items.get(66).addInRecette(new ElementRecette(items.get(8),8));
        // Jambière en fer + forge
        items.get(68).addInRecette(new ElementRecette(items.get(8),6));
        // Botte en fer + forge
        items.get(70).addInRecette(new ElementRecette(items.get(8),4));

        // Casque en DELJCCnium + forge
        items.get(65).addInRecette(new ElementRecette(items.get(10),5));
        // Plastron en DELJCCnium + forge
        items.get(66).addInRecette(new ElementRecette(items.get(10),8));
        // Jambière en DELJCCnium + forge
        items.get(68).addInRecette(new ElementRecette(items.get(10),6));
        // Botte en DELJCCnium + forge
        items.get(70).addInRecette(new ElementRecette(items.get(10),4));

        // Flèche : 1 Bois 1 fer + forge
        items.get(77).addInRecette(new ElementRecette(items.get(3),1));
        items.get(77).addInRecette(new ElementRecette(items.get(8),1));

        // Arc : 3 bois 2 fer + forge
        items.get(78).addInRecette(new ElementRecette(items.get(3),3));
        items.get(78).addInRecette(new ElementRecette(items.get(8),2));

        // Balle en plomb : 2 fer + forge
        items.get(80).addInRecette(new ElementRecette(items.get(8),2));
    }

    public void initializeBlocConstruction() {
        for (Integer integer : items.keySet()) {
            // Sans bloc
            if (items.get(integer).getProvenance() == items.get(11)) {
                ((BlocConstruction) items.get(11)).addRecette(items.get(integer).getCodeObjet(), items.get(integer).getAttributRecette());
            }

            // Etabli
            else if (items.get(integer).getProvenance() == items.get(12)) {
                ((BlocConstruction) items.get(12)).addRecette(items.get(integer).getCodeObjet(), items.get(integer).getAttributRecette());
            }

            // Forge
            else if (items.get(integer).getProvenance() == items.get(13)) {
                ((BlocConstruction) items.get(13)).addRecette(items.get(integer).getCodeObjet(), items.get(integer).getAttributRecette());
            }

            // Alambique
            else if (items.get(integer).getProvenance() == items.get(14)) {
                ((BlocConstruction) items.get(14)).addRecette(items.get(integer).getCodeObjet(), items.get(integer).getAttributRecette());
            }
            // Four
            else if (items.get(integer).getProvenance() == items.get(15)) {
                System.out.println("Rentré ici four haha");
                ((BlocConstruction) items.get(15)).addRecette(items.get(integer).getCodeObjet(), items.get(integer).getAttributRecette());
            }
        }

        System.out.println(items.get(15).getName());

        System.out.println("Liste recette four" + ((BlocConstruction) items.get(15)).getListeRecette().size());
    }

    public void testCraft() {
        getJoueur().ajouterItem(items.get(3),96);
    }

}