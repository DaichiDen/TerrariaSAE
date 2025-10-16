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
    private static Jeu uniqueJeu = null;

    private ArrayList<Ennemi> ennemis;
    private ArrayList<PNJ> pNJ;
    private ObservableList<Ennemi> mobs;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;
    private Boolean arretTemps = false;

    public final static int taille1bloc = 32;



    private Jeu(){
        ennemis = new ArrayList<>();
        pNJ = new ArrayList<>();
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
    public void removeMob(Entite entite){
        mobs.remove(entite);
    }

    public void addEnnemis(Ennemi ennemi) {
        ennemis.add(ennemi);
    }
    public void removeEnnemi(Ennemi ennemi){
        ennemis.remove(ennemi);
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

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public ArrayList<PNJ> getpNJ() {
        return pNJ;
    }

    public void déinitialisationMobs() {
        int i = Jeu.getUniqueJeu().getEnnemis().size()-1;
        while (i >= 0) {
            this.getEnnemis().get(i).decrementVie(Jeu.getUniqueJeu().getEnnemis().get(i).getBarreVie().getVieMax());

            i--;
        }
    }
    public void initialisationMobs () {
        Ennemi ogre = new Ogre("Pierre l'ogre vert", 50, 20, 3000, 0, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
        Ennemi ogre2 = new Ogre("Pierre l'ogre vert pale", 50, 20, 1340, 1340, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
        Ennemi ogre3 = new Ogre("Pierre l'ogre vert foncé", 50, 20, 4962, 1376, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
        Ennemi ogre4 = new Ogre("Pierre l'ogre vert clair", 50, 20, 3068, 1600, 0, 4, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 3);
        Ennemi goblin = new Goblin("Caillou le gobelin vert", 20, 20, 5000, 0, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
        Ennemi goblin2 = new Goblin("Caillou le gobelin vert pale", 20, 20, 1456, 1728, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
        Ennemi goblin3 = new Goblin("Caillou le gobelin vert foncé", 20, 20, 2959, 1088, 0, 2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
        Ennemi goblin4 = new Goblin("Caillou le gobelin vert clair", 20, 20, 5238, 1760, 0,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
        Ennemi goblin5 = new Goblin("Caillou le gobelin vert émeraude", 20, 20, 4544, 1632, 0,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 10, 8);
        Ennemi mh = new MH("Monsieur Homps", 250, 20, 4500, 0, 5,  2, Jeu.getUniqueJeu().getTaille1bloc(), Jeu.getUniqueJeu().getTaille1bloc() * 2, 15, 8);

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
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(3),96);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(5),96);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(6),20);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(7),20);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(21),50);
    }

}
