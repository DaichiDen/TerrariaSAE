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
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.HashMap;

// Environnement du jeu
public class Jeu {
    private static Jeu uniqueJeu = null;

    private Map carte;
    private TilePane tp;
    private ArrayList<Ennemi> ennemis;
    private ArrayList<PNJ> pNJ;
    private ObservableList<Ennemi> mobs;
    // projectiles
    private ArrayList<Projectile> projectiles;
    private ObservableList<Projectile> liste_projectiles;

    public final static int taille1bloc = 32;

    private Jeu(String nomJoueur){
        carte = new Map();
        ennemis = new ArrayList<>();
        pNJ = new ArrayList<>();
        mobs = FXCollections.observableArrayList(ennemis);
        projectiles= new ArrayList<>();
        liste_projectiles = FXCollections.observableArrayList(projectiles);
    }

    public static Jeu getUniqueJeu() {
        if(uniqueJeu == null) {
            uniqueJeu = new Jeu("Joueur");
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

                if (Joueur.getUniqueJoueur().isTimeStop()) {
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

                    if (!p.getType().equals("balle")) {
                        p.setForceY(p.getForceY() + p.getGravité());
                    }

                    p.setY(p.getY() + (int) p.getForceY());

                    if (p.collisionVerticale() || p.collisionHorizontale()) {
                        if(p.getType().equals("boule_de_feu")){
                            p.explosion();
                        }
                        p.setActif(false);
                        getListe_projectiles().remove(i);
                    }
                }

                for(int j = 0; j < mobs.size(); j++ ){ //dégâts sur les entités vivantes
                    if(mobs.get(j).getHitbox().intersects(p.getHitbox())){
                        mobs.get(j).decrementVie(p.getAttaque());
                        p.setActif(false);
                        getListe_projectiles().remove(i);
                    }else if (Joueur.getUniqueJoueur().getHitbox().intersects(p.getHitbox())){
                        Joueur.getUniqueJoueur().decrementVie(p.getAttaque());
                        p.setActif(false);
                        getListe_projectiles().remove(i);
                    }
                }
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

    public Map getCarte(){
        return carte;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public ArrayList<PNJ> getpNJ() {
        return pNJ;
    }

    public void testCraft() {
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(3),96);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(5),96);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(6),20);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(7),20);
        Joueur.getUniqueJoueur().ajouterItem(ListeItems.getItemParId(21),50);
    }

}