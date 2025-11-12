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
    private ArrayList<PNJ> pNJ;
    private ObservableList<Ennemi> mobs;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;
    private Boolean arretTemps = false;
    private FabriqueDifficulteEnnemis fabriqueDEnnemis; // il aurait été possible de choisir la difficulté (ce qui impacte le type et le nombre de mobs qu'il y a dans le jeu) dans le menu start.

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
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(78),1);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(77),64);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(3),96); // Bois
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(5),96); // Pierre
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(6),20); // Minerai Fer
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(7),20); // Minerai DelJCNium
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(21),50); // Charbon
    }

}
