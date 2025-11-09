package fr.iut.saeterraria.sae.Modele;

import fr.iut.saeterraria.sae.Modele.Objets.*;
import fr.iut.saeterraria.sae.Modele.Entites.Projectile;
import fr.iut.saeterraria.sae.Modele.Entites.*;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocConstructionSansBloc;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocCraft;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocForge;
import fr.iut.saeterraria.sae.Modele.Objets.Etablis.BlocFour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.util.ArrayList;

// Environnement du jeu
public class Jeu {
    private static Jeu uniqueJeu = null;

    private ArrayList<Ennemi> ennemis;
    private ObservableList<Ennemi> mobs;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;
    private Boolean arretTemps = false;

    public final static int taille1bloc = 32;



    private Jeu(){
        ennemis = new ArrayList<>();
        mobs = FXCollections.observableArrayList(ennemis);
        projectiles= new ArrayList<>();
        liste_projectiles = FXCollections.observableArrayList(projectiles);
    }

    public static Jeu getUniqueJeu() {
        if(uniqueJeu == null) {
            uniqueJeu = new Jeu();
        }
        return uniqueJeu;
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

                p.màjProjectile();

                if (p.collisionVerticale() || p.collisionHorizontale()) {
                    supprimerProjectile(p, i);
                }

                appliquerDegats(p, i);
            }
        }
    }

    public void supprimerProjectile(Projectile p, int ind){
        p.action();
        p.setActif(false);
        getListe_projectiles().remove(ind);
    }

    public void appliquerDegats(Projectile p, int ind) {
        for (int j = 0; j < mobs.size(); j++) { //dégâts sur les entités vivantes
            if (mobs.get(j).getHitbox().intersects(p.getHitbox())) {
                mobs.get(j).decrementVie(p.getAttaque());
                p.setActif(false);
                getListe_projectiles().remove(ind);
            } else if (Joueur.getUniqueJoueur().getHitbox().intersects(p.getHitbox())) {
                Joueur.getUniqueJoueur().decrementVie(p.getAttaque());
                p.setActif(false);
                getListe_projectiles().remove(ind);
            }
        }
    }


    public ObservableList<Ennemi> getMobs() {
        return mobs;
    }
    public void addMobs(Ennemi entite){
        mobs.add(entite);
    }

    public void addEnnemis(Ennemi ennemi) {
        ennemis.add(ennemi);
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void déinitialisationMobs() {
        int i = Jeu.getUniqueJeu().getEnnemis().size()-1;
        while (i >= 0) {
            this.getEnnemis().get(i).decrementVie(Jeu.getUniqueJeu().getEnnemis().get(i).getBarreVie().getVieMax());

            i--;
        }
    }
    public void initialisationMobs () {
        Ennemi ogre = new Ogre( 3000, 0);
        Ennemi ogre2 = new Ogre(1340, 1340);
        Ennemi ogre3 = new Ogre(4962, 1376);
        Ennemi ogre4 = new Ogre(3068, 1600);
        Ennemi goblin = new Goblin(5000, 0);
        Ennemi goblin2 = new Goblin(1456, 1728);
        Ennemi goblin3 = new Goblin(2959, 1088);
        Ennemi goblin4 = new Goblin(5238, 1760);
        Ennemi goblin5 = new Goblin(4544, 1632);
        Ennemi mh = new MH(4500, 0);

        this.addEnnemis(ogre);
       this.addEnnemis(ogre2);
        this.addEnnemis(ogre3);
        this.addEnnemis(ogre4);
        this.addMobs(ogre);
        this.addMobs(ogre2);
        this.addMobs(ogre3);
        this.addMobs(ogre4);

        this.addEnnemis(goblin);
        this.addEnnemis(goblin2);
        this.addEnnemis(goblin3);
        this.addEnnemis(goblin4);
        this.addEnnemis(goblin5);
        this.addMobs(goblin);
        this.addMobs(goblin2);
        this.addMobs(goblin3);
        this.addMobs(goblin4);
        this.addMobs(goblin5);

        this.getUniqueJeu().addEnnemis(mh);
        this.getUniqueJeu().addMobs(mh);

    }

    public void setArretTemps(boolean timeStop) {
        this.arretTemps =timeStop;
    }
    public Boolean getArretTemps(){
        return arretTemps;
    }

    public void initialisationJoueur(){
        Joueur.getUniqueJoueur().getBarreVie().setVie(Joueur.getUniqueJoueur().getBarreVie().getVieMax());
        Joueur.getUniqueJoueur().setEstVivant(true);
        Joueur.getUniqueJoueur().setX(20*32);
        Joueur.getUniqueJoueur().setY(0*32);
    }

    public void testCraft() {
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(3),96); // Bois
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(5),96); // Pierre
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(6),20); // Minerai Fer
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(7),20); // Minerai DelJCNium
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(21),50); // Charbon
    }

}
