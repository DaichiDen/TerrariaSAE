package fr.iut.saeterraria.sae.Modele;

import fr.iut.saeterraria.sae.Modele.Objets.*;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.DashingKatana;
import fr.iut.saeterraria.sae.Modele.Objets.Arme.Distance;
import fr.iut.saeterraria.sae.Modele.Personnages.Projectile;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstruction;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Hache;
import fr.iut.saeterraria.sae.Modele.Objets.Outil.Pelle;
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
        joueur = new Joueur(nomJoueur, this, (Pierre_TP) items.get(49));
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
            for (int i = 0; i < this.getListe_projectiles().size(); i++) {
                if (!getJoueur().isTimeStop()) {

                    this.getListe_projectiles().get(i).setX(this.getListe_projectiles().get(i).getX() + this.getListe_projectiles().get(i).getForceX());

                    if (!this.getListe_projectiles().get(i).getNom().equals("Balle en plomb")) {
                        this.getListe_projectiles().get(i).setForceY(this.getListe_projectiles().get(i).getForceY() + this.getListe_projectiles().get(i).getGravité());
                    }

                    this.getListe_projectiles().get(i).setY(this.getListe_projectiles().get(i).getY() + this.getListe_projectiles().get(i).getForceY());

                    if (projectiles.get(i).collisionVerticale(getListe_projectiles().get(i).getTailleL(), getListe_projectiles().get(i).getTailleH()) || projectiles.get(i).collisionHorizontale(getListe_projectiles().get(i).getTailleL(), getListe_projectiles().get(i).getTailleH())) {
                        getListe_projectiles().get(i).setActif(false);
                        getListe_projectiles().remove(i);
                    }
                }else{
                    if(this.getListe_projectiles().get(i).getNom().equals("Balle en plomb")){
                        this.getListe_projectiles().get(i).setX(this.getListe_projectiles().get(i).getX() + this.getListe_projectiles().get(i).getForceX());

                        this.getListe_projectiles().get(i).setY(this.getListe_projectiles().get(i).getY() + this.getListe_projectiles().get(i).getForceY());

                        if (projectiles.get(i).collisionVerticale(getListe_projectiles().get(i).getTailleL(), getListe_projectiles().get(i).getTailleH()) || projectiles.get(i).collisionHorizontale(getListe_projectiles().get(i).getTailleL(), getListe_projectiles().get(i).getTailleH())) {
                            getListe_projectiles().get(i).setActif(false);
                            getListe_projectiles().remove(i);
                        }
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
        items.put(3, new Bloc("Bois","Element indispensable, base de créativité",1,3));
        items.put(4, new Bloc("Minerai Charbon","Bloc contenant plusieurs charbons",2,3));
        items.put(5, new Item("Charbon","Permet d'alimenter le four et la forge en chaleur",1));
        items.put(6, new Bloc("Pierre","Bloc basique de pierre commun dans les sous-sol",2,5));
        items.put(7, new Bloc("Minerai Fer","Métal commun de Fer",3,7));
        items.put(8, new Item("Fer","Métal obtenu en fondant des Minerai de Fer",1));
        items.put(9, new Bloc("Glace","",1,2));
        items.put(10, new Item(" Minerai DELJCCium", "", 1));
        items.put(11,new BlocConstruction("ConstructionSansBloc","",0,0));
        items.put(12, new BlocConstruction("Etabli","Un établi qui permet la fabrication d'objets",1,3, (BlocConstruction) items.get(11)));
        items.put(13, new BlocConstruction("Forge","Un établi qui permet la fabrication d'objets",1,3, (BlocConstruction) items.get(12) ));
        items.put(14, new BlocConstruction("Alambique","Un établi qui permet la fabrication d'objets",1,3,(BlocConstruction) items.get(12)));
        items.put(15, new BlocConstruction("Four","Permet de fondre et cuire ses objets",1,3,(BlocConstruction) items.get(12)));

        items.put(16, new Bloc("Minerai DELJCCium", "", 3, 9));

        items.get(8).setProvenance((BlocConstruction) items.get(15));
        items.get(10).setProvenance((BlocConstruction) items.get(15));

        for (int i=17; i<=23; i++) {
            items.put(i, new Pelle("",""));
        }



        //Bloc outil
        items.put(24, new Coffre("Coffre", "", 1, 3,(BlocConstruction) items.get(12)));


        for (int i = 25; i < 49; i++) {
            items.put(i,new Item("","",1));

        }

        // Outils
        items.put(49, new Pierre_TP());

        items.put(50,new Pelle("Pelle de bois","Une pelle en bois ordinaire, accélère la vitesse pour creuser des objets",(BlocConstruction) items.get(12)));
        items.put(51,new Hache("Hache de bois","",(BlocConstruction) items.get(12)));
        items.put(52,new Pioche("Pioche de bois","",(BlocConstruction) items.get(12)));

        items.put(53,new Pelle("Pelle de pierre","", (BlocConstruction) items.get(12)));
        items.put(54,new Hache("Hache de pierre","",(BlocConstruction) items.get(12)));
        items.put(55,new Pioche("Pioche de pierre","",(BlocConstruction) items.get(12)));

        items.put(56,new Pelle("Pelle de fer","",(BlocConstruction) items.get(13)));
        items.put(57,new Hache("Hache de fer","",(BlocConstruction) items.get(13)));
        items.put(58,new Pioche("Pioche de fer","",(BlocConstruction) items.get(13)));

        items.put(59,new Pelle("Pelle DELJCCium","Une pelle en DELJCCium exclusive, accélère fortement la vitesse pour creuser des objets",(BlocConstruction) items.get(13)));
        items.put(60,new Hache("Hache DELJCCium","",(BlocConstruction) items.get(13)));
        items.put(61,new Pioche("Pioche DELJCCium","",(BlocConstruction) items.get(13)));

        items.put(62, new Item("Seau", "", 1,(BlocConstruction) items.get(13)));

        // Armures

        items.put(63,new Armure("Casque en fer","",2, (BlocConstruction) items.get(13)));
        items.put(64,new Armure("Casque DELJCCium","",3,(BlocConstruction) items.get(13)));
        items.put(65,new Armure("Plastron en fer","",6,(BlocConstruction) items.get(13)));
        items.put(66,new Armure("Plastron DELJCCium","",9,(BlocConstruction) items.get(13)));
        items.put(67,new Armure("Jambière en fer","",4,(BlocConstruction) items.get(13)));
        items.put(68,new Armure("Jambière DELJCCium","",6,(BlocConstruction) items.get(13)));
        items.put(69,new Armure("Botte en fer","",3,(BlocConstruction) items.get(13)));
        items.put(70,new Armure("Botte DELJCCium","",5,(BlocConstruction) items.get(13) ) );

        // Armes
        items.put(71,new DashingKatana("Katana étrange","Ce Katana semble pouvoir octroyer la capacité à son détenteur de se déplacer à la vitesse du son",10));
        // Autres items
        items.put(72, new Item("Flèche","Flèche",1,(BlocConstruction) items.get(12)));
        items.put(73, new Distance("Arc en bois","Un vieil arc usé",10,(BlocConstruction) items.get(12)));
        items.put(74, new Distance("Arquebuse","Etrange objet qui semble ralentir le temps",5));
        items.put(75, new Item("Balle en plomb","Un projectile qui peut être utlisé ",1));

    }

    private void initializeRecettes() {

        // Lingot de fer : 1 Minérai de fer et 1 charbon et 1 four
        items.get(8).addInRecette(new ElementRecette(items.get(7),1));
        items.get(8).addInRecette(new ElementRecette(items.get(4),1));

        // Lingot de DELJCCium : 1 Minérai de DELJCCium et 1 charbon et 1 four
        items.get(10).addInRecette(new ElementRecette(items.get(16),1));
        items.get(10).addInRecette(new ElementRecette(items.get(4),1));


        //Etabli : 4 bois
        items.get(12).addInRecette(new ElementRecette(items.get(3),4));

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

        // Pelle de bois : 3 bois
        items.get(50).addInRecette(new ElementRecette(items.get(3),3));
        // Pelle de pierre : 3 pierre
        items.get(53).addInRecette(new ElementRecette(items.get(6),3));
        // Pelle de fer : 3 fer + forge
        items.get(56).addInRecette(new ElementRecette(items.get(8),3));
        // Pelle en DELJCCnium
        items.get(59).addInRecette(new ElementRecette(items.get(10),3));

        // Hache de bois : 3 bois
        items.get(51).addInRecette(new ElementRecette(items.get(3),3));
        // Hache de pierre : 3 pierre
        items.get(54).addInRecette(new ElementRecette(items.get(6),3));
        // Hache de fer : 3 fer + forge
        items.get(57).addInRecette(new ElementRecette(items.get(8),3));
        // Hache en DELJCCnium
        items.get(60).addInRecette(new ElementRecette(items.get(10),3));

        // Pioche de bois : 3 bois
        items.get(52).addInRecette(new ElementRecette(items.get(3),3));
        // Pioche de pierre : 3 pierre
        items.get(55).addInRecette(new ElementRecette(items.get(6),3));
        // Pioche de fer : 3 fer + forge
        items.get(58).addInRecette(new ElementRecette(items.get(8),3));
        // Pioche en DELJCCnium
        items.get(61).addInRecette(new ElementRecette(items.get(10),3));

        // Casque en fer + forge
        items.get(62).addInRecette(new ElementRecette(items.get(8),5));
        // Plastron en fer + forge
        items.get(64).addInRecette(new ElementRecette(items.get(8),8));
        // Jambière en fer + forge
        items.get(66).addInRecette(new ElementRecette(items.get(8),6));
        // Botte en fer + forge
        items.get(68).addInRecette(new ElementRecette(items.get(8),4));

        // Casque en DELJCCnium + forge
        items.get(63).addInRecette(new ElementRecette(items.get(10),5));
        // Plastron en DELJCCnium + forge
        items.get(65).addInRecette(new ElementRecette(items.get(10),8));
        // Jambière en DELJCCnium + forge
        items.get(67).addInRecette(new ElementRecette(items.get(10),6));
        // Botte en DELJCCnium + forge
        items.get(69).addInRecette(new ElementRecette(items.get(10),4));
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
